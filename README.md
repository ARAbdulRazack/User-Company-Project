## User Company Project

Developed a small scale project using Spring Boot, focusing on user and company management. 

## Project Setup

- Clone the repository to your local machine:
git clone https://github.com/yourusername/User-Company-Project.git

## Opening in IntelliJ	 

- Open Project: Open your project in IntelliJ IDEA.
- Locate the Project: Find the project in your desktop.
- POM.xml: In the pom.xml file, you can find the "build" section where project build configurations are specified. In that comment the build configurations.

## Database Setup

- Create PostgreSQL Database: Create a PostgreSQL database according to the database name specified in the application properties. Use the command:
	CREATE DATABASE UserCompany;
- For example, if the database URL in application.properties is spring.datasource.url=jdbc:postgresql://localhost:5432/UserCompany, then the database name would be UserCompany.

## Application Properties

- Change Username and Password: Update the username and password in the application.properties file to match your PostgreSQL credentials.

## Running in Terminal

- Run the command given below:
- To clean Maven and install Maven: mvn clean install
- To Run the Project: mvn spring-boot:run

## Running the Application

- Port: The project runs on port 8010.
