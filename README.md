# TA Placement System Service

## Basic Overview
The TA Placement System is an application that is used by students to apply to be a computer science teaching assistant, administrators to appoint and place teaching assistants, and teaching assistants to view their placements. 

## Git Repo
This git repo contains the API for the TA Placement System. placement-system contains the front-end code.

## Database

### User Table 

The Username is unique.

<table>
  <tr> 
    <th>Column</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>Id</td>
    <td>Primary Key</td>
  </tr>
  <tr>
    <td>Username</td>
    <td>Login for user</td>
  </tr>
  <tr>
    <td>Password</td>
    <td>Encrypted password of user</td>
  </tr>
</table>

### Authority Table 

<table>
  <tr> 
    <th>Column</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>Id</td>
    <td>Primary Key</td>
  </tr>
  <tr>
    <td>Username</td>
    <td>Foreign Key to Username in User table</td>
  </tr>
  <tr>
    <td>Authority</td>
    <td>Type of user: student, admin, or ta</td>
  </tr>
</table>

### Course Table 

<table>
  <tr> 
    <th>Column</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>Id</td>
    <td>Primary Key</td>
  </tr>
  <tr>
    <td>DeptCode</td>
    <td>Code for the department of the course (like CSCI for Computer Science)</td>
  </tr>
  <tr>
    <td>CourseNumber</td>
    <td>Number for the course (like 101)</td>
  </tr>
  <tr>
    <td>CourseName</td>
    <td>Name of the course</td>
  </tr>
</table>

### CourseSection Table 

<table>
  <tr> 
    <th>Column</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>Id</td>
    <td>Primary Key</td>
  </tr>
  <tr>
    <td>CourseId</td>
    <td>Foreign Key to Id in Course table</td>
  </tr>
  <tr>
    <td>Professor</td>
    <td>Instructor for the course</td>
  </tr>
  <tr>
    <td>DaysOfWeek</td>
    <td>Days for the section</td>
  </tr>
  <tr>
    <td>Time</td>
    <td>Time for the section</td>
  </tr>
  <tr>
    <td>Building</td>
    <td>Building for the section</td>
  </tr>
  <tr>
    <td>Room</td>
    <td>Room for the section</td>
  </tr>
</table>

### Application Table 

<table>
  <tr> 
    <th>Column</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>Id</td>
    <td>Primary Key</td>
  </tr>
  <tr>
    <td>UserId</td>
    <td>Foreign Key to Id in User table</td>
  </tr>
  <tr>
    <td>Name</td>
    <td>Name of student applying</td>
  </tr>
  <tr>
    <td>Email</td>
    <td>Email of student applying</td>
  </tr>
  <tr>
    <td>IdNumber</td>
    <td>Student ID Number</td>
  </tr>
  <tr>
    <td>Gpa</td>
    <td>Student's Grade Point Average</td>
  </tr>
  <tr>
    <td>GraduationDate</td>
    <td>Graduation date of applying student</td>
  </tr>
  <tr>
    <td>Major</td>
    <td>Student's major</td>
  </tr>
</table>
