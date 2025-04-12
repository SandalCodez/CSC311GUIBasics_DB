#CSC311GUIBAsics_DB

## Overview
This application is a JavaFX-based GUI that provides an interface to a MySQL database. It allows users to sign in, view, add, edit, and delete user records stored in a MySQL database hosted on Azure.

## Features
- User authentication system
- CRUD operations (Create, Read, Update, Delete) for user records
- Dark mode toggle
- Table view of database records
- Image upload functionality

## Project Structure
- `ConnDBOpsCopy.java` - Handles database connections and operations
- `DB_Application.java` - Main application class with scene management
- `DB_GUI_Controller.java` - Controls the main database interface
- `Person.java` - Data model class for user records
- `SIgnIn_Controller.java` - Controls the sign-in interface

## Setup and Configuration
1. Ensure you have JavaFX and MySQL connector dependencies in your project
2. The database connection is configured to connect to an Azure MySQL instance:
   - Server: csc311sandoval27.mysql.database.azure.com
   - Database: DBname
   - Username and password are hardcoded in the ConnDBOpsCopy class

## Usage
1. Run the application by executing the `DB_Application` class
2. Sign in with the credentials:
   - Username: admin
   - Password: pass
3. Once signed in, you can:
   - View all records from the database
   - Add new records
   - Edit existing records
   - Delete records
   - Upload images

## Database Schema
The application uses a table called `users1` with the following columns:
- `fname` (VARCHAR) - First name
- `lname` (VARCHAR) - Last name (unique)
- `department` (VARCHAR) - Department
- `major` (VARCHAR) - Major

## Future Improvements
- Move database credentials to a configuration file
- Add proper error handling for database operations
- Implement secure password storage
- Add user registration functionality
- Improve the data validation and user feedback

## Security Notes
- Current implementation has hardcoded database credentials which is not recommended for production use
- Authentication is basic and doesn't connect with the database

## Requirements
- Java 8 or higher
- JavaFX
- MySQL Connector/J
