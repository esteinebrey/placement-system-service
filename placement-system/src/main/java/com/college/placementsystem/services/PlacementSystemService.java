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
import com.college.placementsystem.model.ApplicationResponse;
import com.college.placementsystem.model.CourseResponse;
import com.college.placementsystem.repositories.ApplicationRepository;
import com.college.placementsystem.repositories.ApplicationCourseRepository;
import com.college.placementsystem.repositories.ApplicationSkillRepository;
import com.college.placementsystem.repositories.CourseRepository;
import com.college.placementsystem.repositories.ProgrammingLanguageRepository;

@Service
public class PlacementSystemService {
	
	@Autowired
	ApplicationRepository applicationRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	ApplicationCourseRepository applicationCourseRepository;
	
	@Autowired
	ApplicationSkillRepository applicationSkillRepository;
	
	public ApplicationResponse findApplication(int userId) {
		Optional<Application> optionalApplication = applicationRepository.findById(userId);
		// Application was found
		if (optionalApplication.isPresent()) {
			Application application = optionalApplication.get();
			ApplicationResponse applicationResponse = new ApplicationResponse();
			// Determine the skills specified on the application
			List<ApplicationSkill> applicationSkills = applicationSkillRepository.findByApplication(application);
			List<String> skills = new ArrayList<String>();
			ProgrammingLanguage programmingLanguage;
			for(ApplicationSkill applicationSkill: applicationSkills) {
				programmingLanguage = applicationSkill.getSkill();
				String skill = programmingLanguage.getSkill();
				skills.add(skill);
			}
			applicationResponse.setSkills(skills);
			// Determine the courses specified on the application
			List<ApplicationCourse> applicationCourses = applicationCourseRepository.findByApplication(application);
			List<CourseResponse> courses = new ArrayList<CourseResponse>();
			for (ApplicationCourse applicationCourse: applicationCourses) {
				CourseResponse course = new CourseResponse();
				course.setDeptCode(applicationCourse.getCourse().getDeptCode());
				course.setName(applicationCourse.getCourse().getCourseName());
				course.setNumber(applicationCourse.getCourse().getCourseNumber());
				courses.add(course);
			}
			// Determine other fields on application
			applicationResponse.setCourses(courses);
			applicationResponse.setName(application.getName());
			applicationResponse.setEmail(application.getEmail());
			applicationResponse.setIdNumber(application.getIdNumber());
			applicationResponse.setGpa(application.getGpa());
			applicationResponse.setGraduationDate(application.getGraduationDate());
			applicationResponse.setMajor(application.getMajor());
			return applicationResponse;
		}
		// No application was found
		else {
			return null;
		}
	}
	
	public List<CourseResponse> findAllCourses() {
		List<Course> courses = courseRepository.findAll();
		List<CourseResponse> courseResponses = new ArrayList<CourseResponse>();
		CourseResponse courseResponse = new CourseResponse();
		for (Course course : courses) {
			courseResponse.setDeptCode(course.getDeptCode());
			courseResponse.setName(course.getCourseName());
			courseResponse.setNumber(course.getCourseNumber());
			courseResponses.add(courseResponse);
		}
		return courseResponses;
	}
}
