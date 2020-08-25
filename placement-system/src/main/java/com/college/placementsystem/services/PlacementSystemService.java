package com.college.placementsystem.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.placementsystem.entities.Application;
import com.college.placementsystem.entities.Course;
import com.college.placementsystem.entities.ProgrammingLanguage;
import com.college.placementsystem.entities.User;
import com.college.placementsystem.model.ApplicationModel;
import com.college.placementsystem.model.CourseModel;
import com.college.placementsystem.model.SkillModel;
import com.college.placementsystem.repositories.ApplicationRepository;
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
//		// Create corresponding entries in ApplicationCourse
		List<Course> correspondingCourses = new ArrayList<Course>();
		for(CourseModel courseModel: submittedApplication.getCourses()) {
			Course course = courseRepository.findById(courseModel.getCourseId());
			correspondingCourses.add(course);		
		}
		savedApplication.setTakenCourses(correspondingCourses);
//		// Create corresponding entries in ApplicationSkill
		List<ProgrammingLanguage> correspondingSkills = new ArrayList<ProgrammingLanguage>();
		for(SkillModel skill: submittedApplication.getSkills()) {
			ProgrammingLanguage programmingLanguage = programmingLanguageRepository.findBySkill(skill.getDescription());
			correspondingSkills.add(programmingLanguage);		
		}
		savedApplication.setCurrentSkills(correspondingSkills);
		return submittedApplication;		
	}
	
	// Get all the courses in the database
	public List<CourseModel> findAllCourses() {
		List<Course> courses = courseRepository.findAll();
		List<CourseModel> courseResponses = new ArrayList<CourseModel>();
		for (Course course : courses) {
			CourseModel courseResponse = new CourseModel();
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
	
	// Get all skills in the database
	public List<SkillModel> findAllSkills() {
		List<SkillModel> skillResponses = new ArrayList<SkillModel>();
		ArrayList<ProgrammingLanguage> programmingLanguages = (ArrayList<ProgrammingLanguage>) programmingLanguageRepository.findAll();
		for (ProgrammingLanguage programmingLanguage: programmingLanguages) {
			SkillModel skillResponse = new SkillModel();
			skillResponse.setSkillId(programmingLanguage.getId());
			skillResponse.setDescription(programmingLanguage.getSkill());
			skillResponses.add(skillResponse);
		}
		return skillResponses;
	}
	
	// Helper Functions
	
	// Map application fields from entity to response
	public static void mapApplicationFields(Application application, ApplicationModel applicationResponse) {
		applicationResponse.setName(application.getName());
		applicationResponse.setEmail(application.getEmail());
		applicationResponse.setIdNumber(application.getIdNumber());
		applicationResponse.setGpa(application.getGpa());
		applicationResponse.setGraduationDate(application.getGraduationDate());
		applicationResponse.setMajor(application.getMajor());
	}
	
	// Get courses present on application for response
	public void mapApplicationCourses(Application application, ApplicationModel applicationResponse) {
		List<Course> applicationCourses = application.getTakenCourses();
		List<CourseModel> courses = new ArrayList<CourseModel>();
		for (Course applicationCourse: applicationCourses) {
			CourseModel course = new CourseModel();
			course.setDeptCode(applicationCourse.getDeptCode());
			course.setName(applicationCourse.getCourseName());
			course.setNumber(applicationCourse.getCourseNumber());
			course.setCourseId(applicationCourse.getId());
			courses.add(course);
		}
		applicationResponse.setCourses(courses);
	}
	
	// Get skills present on application for response
	public void mapApplicationSkills(Application application, ApplicationModel applicationResponse) {
		List<ProgrammingLanguage> applicationSkills = application.getCurrentSkills();
		List<SkillModel> skills = new ArrayList<SkillModel>();
		for(ProgrammingLanguage applicationSkill: applicationSkills) {
			SkillModel skill = new SkillModel();
			skill.setSkillId(applicationSkill.getId());
			skill.setDescription(applicationSkill.getSkill());
			skills.add(skill);
		}
		applicationResponse.setSkills(skills);
	}
}
