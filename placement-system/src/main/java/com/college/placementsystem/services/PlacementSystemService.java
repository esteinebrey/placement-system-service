package com.college.placementsystem.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.placementsystem.entities.Application;
import com.college.placementsystem.entities.ApplicationCourse;
import com.college.placementsystem.entities.ApplicationSkill;
import com.college.placementsystem.entities.Course;
import com.college.placementsystem.entities.ProgrammingLanguage;
import com.college.placementsystem.entities.User;
import com.college.placementsystem.model.ApplicationModel;
import com.college.placementsystem.model.CourseModel;
import com.college.placementsystem.repositories.ApplicationRepository;
import com.college.placementsystem.repositories.ApplicationCourseRepository;
import com.college.placementsystem.repositories.ApplicationSkillRepository;
import com.college.placementsystem.repositories.CourseRepository;
import com.college.placementsystem.repositories.ProgrammingLanguageRepository;
import com.college.placementsystem.repositories.UserRepository;

@Service
public class PlacementSystemService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ApplicationRepository applicationRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	ApplicationCourseRepository applicationCourseRepository;
	
	@Autowired
	ApplicationSkillRepository applicationSkillRepository;
	
	@Autowired
	ProgrammingLanguageRepository programmingLanguageRepository;
	
	// Get the application with the course and skill info for the user specified, if it exists
	public ApplicationModel findApplication(int userId) {
		User user = userRepository.findById(userId);
		Optional<Application> optionalApplication = applicationRepository.findByUserId(user);
		// Application was found
		if (optionalApplication.isPresent()) {
			Application application = optionalApplication.get();
			ApplicationModel applicationResponse = new ApplicationModel();
			// Determine the skills specified on the application
			mapApplicationSkills(application, applicationResponse);
			// Determine the courses specified on the application
			mapApplicationCourses(application, applicationResponse);
			mapApplicationFields(application, applicationResponse);
			return applicationResponse;
		}
		// No application was found
		else {
			return null;
		}
	}
	
	// Save submitted application
	public ApplicationModel submitApplication(int userId, ApplicationModel submittedApplication) {
		Application application = new Application();
		application.setName(submittedApplication.getName());
		application.setEmail(submittedApplication.getEmail());
		application.setIdNumber(submittedApplication.getIdNumber());
		application.setGpa(submittedApplication.getGpa());
		application.setMajor(submittedApplication.getMajor());
		application.setGraduationDate(submittedApplication.getGraduationDate());
		// Find the user for the application
		User user = userRepository.findById(userId);
		application.setUser(user);
		// Save and get saved application to add entries in ApplicationCourse and ApplicationSkill
		Application savedApplication = applicationRepository.save(application);
		// Create corresponding entries in ApplicationCourse
		for(CourseModel courseModel: submittedApplication.getCourses()) {
			ApplicationCourse applicationCourse = new ApplicationCourse();
			Course course = courseRepository.findById(courseModel.getCourseId());
			applicationCourse.setApplication(savedApplication);
			applicationCourse.setCourse(course);
			applicationCourseRepository.save(applicationCourse);			
		}
		// Create corresponding entries in ApplicationSkill
		for(String skill: submittedApplication.getSkills()) {
			ApplicationSkill applicationSkill = new ApplicationSkill();
			ProgrammingLanguage programmingLanguage = programmingLanguageRepository.findBySkill(skill);
			applicationSkill.setApplication(savedApplication);
			applicationSkill.setSkill(programmingLanguage);
			applicationSkillRepository.save(applicationSkill);			
		}
		return submittedApplication;		
	}
	
	// Get all the courses in the database
	public List<CourseModel> findAllCourses() {
		List<Course> courses = courseRepository.findAll();
		List<CourseModel> courseResponses = new ArrayList<CourseModel>();
		CourseModel courseResponse = new CourseModel();
		for (Course course : courses) {
			courseResponse.setDeptCode(course.getDeptCode());
			courseResponse.setName(course.getCourseName());
			courseResponse.setNumber(course.getCourseNumber());
			courseResponse.setCourseId(course.getId());
			courseResponses.add(courseResponse);
		}
		return courseResponses;
	}
	
	// Get all the applications in the database
	public List<ApplicationModel> findAllApplications() {
		List<ApplicationModel> applicationResponses = new ArrayList<ApplicationModel>();
		ArrayList<Application> applications = (ArrayList<Application>) applicationRepository.findAll();
		for(Application application: applications) {
			ApplicationModel applicationResponse = new ApplicationModel();
			// Map fields from database to response
			mapApplicationFields(application, applicationResponse);
			mapApplicationCourses(application, applicationResponse);
			mapApplicationSkills(application, applicationResponse);
			applicationResponses.add(applicationResponse);
		}
		return applicationResponses;			
	}
	
	// Helper Functions
	
	// Map application fields from entity to response
	public void mapApplicationFields(Application application, ApplicationModel applicationResponse) {
		applicationResponse.setName(application.getName());
		applicationResponse.setEmail(application.getEmail());
		applicationResponse.setIdNumber(application.getIdNumber());
		applicationResponse.setGpa(application.getGpa());
		applicationResponse.setGraduationDate(application.getGraduationDate());
		applicationResponse.setMajor(application.getMajor());
	}
	
	// Get courses present on application for response
	public void mapApplicationCourses(Application application, ApplicationModel applicationResponse) {
		List<ApplicationCourse> applicationCourses = applicationCourseRepository.findByApplication(application);
		List<CourseModel> courses = new ArrayList<CourseModel>();
		for (ApplicationCourse applicationCourse: applicationCourses) {
			CourseModel course = new CourseModel();
			course.setDeptCode(applicationCourse.getCourse().getDeptCode());
			course.setName(applicationCourse.getCourse().getCourseName());
			course.setNumber(applicationCourse.getCourse().getCourseNumber());
			course.setCourseId(applicationCourse.getCourse().getId());
			courses.add(course);
		}
		applicationResponse.setCourses(courses);
	}
	
	// Get skills present on application for response
	public void mapApplicationSkills(Application application, ApplicationModel applicationResponse) {
		List<ApplicationSkill> applicationSkills = applicationSkillRepository.findByApplication(application);
		List<String> skills = new ArrayList<String>();
		ProgrammingLanguage programmingLanguage;
		for(ApplicationSkill applicationSkill: applicationSkills) {
			programmingLanguage = applicationSkill.getSkill();
			String skill = programmingLanguage.getSkill();
			skills.add(skill);
		}
		applicationResponse.setSkills(skills);
	}
}
