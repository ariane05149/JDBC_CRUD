CREATE TABLE students (
 id SERIAL PRIMARY KEY,
 first_name VARCHAR(50),
 last_name VARCHAR(50),
 email VARCHAR(100) UNIQUE,
 date_of_birth DATE
);
CREATE TABLE courses (
 id SERIAL PRIMARY KEY,
 course_name VARCHAR(100) UNIQUE,
 course_description TEXT
);
CREATE TABLE marks (
 student_id INT,
 course_id INT,
 marks FLOAT,
 PRIMARY KEY(student_id, course_id),
 FOREIGN KEY(student_id) REFERENCES students(id) ON DELETE CASCADE,
 FOREIGN KEY(course_id) REFERENCES courses(id) ON DELETE CASCADE
);