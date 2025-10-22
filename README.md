# HU3 - Product Inventory Management System

## 📖 Description

HU3 is a Java-based inventory management system designed to manage product information in a small retail environment. The application provides a user-friendly graphical interface using Java Swing to perform complete CRUD (Create, Read, Update, Delete) operations on products stored in a MySQL database.

This project demonstrates the implementation of a layered architecture with separation of concerns, including domain models, repositories, services, and user interface components.

## ✨ Features

- **Add Products**: Register new products with name, price, and stock information
- **View Inventory**: Display all products in a formatted table view
- **Update Product Price**: Modify the price of existing products
- **Update Product Stock**: Adjust the stock levels of existing products
- **Delete Products**: Remove products from the inventory with confirmation
- **Search by Name**: Find products by partial name matching
- **Operation Summary**: Track the number of additions, updates, and deletions performed
- **Input Validation**: Comprehensive validation for all user inputs
- **Error Handling**: User-friendly error messages with proper exception handling

## 🛠️ Technologies Used

### Core Technologies
- **Java 21**: Programming language and runtime environment
- **Maven**: Dependency management and build automation
- **MySQL 8.0**: Relational database management system
- **JDBC**: Java Database Connectivity for database operations

### Java Libraries
- **Java Swing**: GUI framework for the desktop application
- **MySQL Connector/J 8.0.33**: JDBC driver for MySQL database connectivity

### Architecture Patterns
- **Layered Architecture**: Separation of concerns across different layers
- **Repository Pattern**: Data access abstraction
- **Service Layer Pattern**: Business logic encapsulation
- **Dependency Injection**: Manual dependency injection for loose coupling

## 🏗️ Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/mycompany/hu3/
│   │       ├── HU3.java                    # Main class (entry point)
│   │       ├── domain/
│   │       │   └── Product.java            # Product entity/domain model
│   │       ├── repository/
│   │       │   ├── Repository.java         # Generic repository interface
│   │       │   ├── ProductRepository.java  # Product-specific repository interface
│   │       │   └── impl/
│   │       │       └── ProductRepositoryImpl.java  # Repository implementation
│   │       ├── service/
│   │       │   ├── ProductService.java     # Service interface
│   │       │   └── impl/
│   │       │       └── ProductServiceImpl.java     # Service implementation
│   │       ├── ui/
│   │       │   └── InitUI.java             # User interface controller
│   │       ├── dbconnection/
│   │       │   └── DbConnection.java       # Database connection utility
│   │       ├── infra/
│   │       │   └── config/
│   │       │       └── AppConfig.java      # Configuration management
│   │       └── databaseSchema.sql          # Database schema definition
│   └── resources/
│       └── application.properties          # Database configuration
└── pom.xml                                # Maven configuration
```

## 🗄️ Database Schema

The application uses a MySQL database with the following schema:

```sql
CREATE DATABASE minitienda_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(60) NOT NULL UNIQUE,
    stock INT NOT NULL,
    price DOUBLE NOT NULL
);
```

## 📋 Prerequisites

Before running the application, ensure you have the following installed:

### Required Software
1. **Java Development Kit (JDK) 21** or higher
   - Download from [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://adoptium.net/)
   - Verify installation: `java -version`

2. **Apache Maven 3.6+**
   - Download from [Maven Downloads](https://maven.apache.org/download.cgi)
   - Verify installation: `mvn -version`

3. **MySQL Server 8.0+**
   - Download from [MySQL Downloads](https://dev.mysql.com/downloads/mysql/)
   - Ensure the MySQL service is running

### Database Setup
1. **Create the database and table**:
   ```sql
   DROP DATABASE IF EXISTS minitienda_db;
   CREATE DATABASE minitienda_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   USE minitienda_db;
   
   CREATE TABLE products (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(60) NOT NULL UNIQUE,
       stock INT NOT NULL,
       price DOUBLE NOT NULL
   );
   ```

2. **Configure database connection** (if needed):
   - Edit `src/main/resources/application.properties`
   - Update the database credentials:
     ```properties
     db.vendor=mysql
     db.name=minitienda_db
     db.user=your_username
     db.password=your_password
     db.port=3306
     db.host=localhost
     db.url=jdbc:mysql://localhost:3306/minitienda_db?useSSL=false&serverTimezone=UTC
     ```

## 🚀 Installation and Setup

### 1. Clone or Download the Project
Navigate to your project directory (assuming the project is already in NetBeansProjects):
```bash
cd /path/to/NetBeansProjects/HU3
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Compile the Project
```bash
mvn compile
```

### 4. Verify Database Connection
Ensure your MySQL server is running and the database `minitienda_db` exists with the proper schema.

## 🏃 Running the Application

### Using Maven (Recommended)
```bash
mvn exec:java -Dexec.mainClass="com.mycompany.hu3.HU3"
```

### Using Java directly
```bash
# Compile first
mvn compile

# Run the generated JAR
java -cp target/classes:target/dependency/* com.mycompany.hu3.HU3
```

### Using the compiled JAR
```bash
# Create the JAR package
mvn package

# Run the JAR
java -jar target/HU3-1.0-SNAPSHOT.jar
```

## 💻 Usage Guide

Once the application starts, you'll see a menu with the following options:

### 1. Add Product
- Select "1. Agregar producto"
- Enter the product name (required, must be unique)
- Enter the price (required, must be greater than 0)
- Enter the initial stock (required, must be 0 or greater)
- Confirm to save the product

### 2. List Inventory
- Select "2. Listar inventario"
- View all products in a formatted table showing ID, Name, Price, and Stock
- If no products exist, you'll see an appropriate message

### 3. Update Product Price
- Select "3. Actualizar precio"
- Enter the product ID
- View current product information
- Enter the new price
- Confirm the update

### 4. Update Product Stock
- Select "4. Actualizar stock"
- Enter the product ID
- View current product information
- Enter the new stock level
- Confirm the update

### 5. Delete Product
- Select "5. Eliminar producto"
- Enter the product ID
- Review product information
- Confirm deletion (this action is irreversible)

### 6. Search by Name
- Select "6. Buscar por nombre"
- Enter a product name (partial matches supported)
- View matching products in a formatted table

### 7. Exit
- Select "7. Salir"
- View operation summary (additions, updates, deletions)
- Application closes

## 🔧 Configuration

### Database Configuration
The application uses `src/main/resources/application.properties` for database configuration:

```properties
db.vendor=mysql
db.name=minitienda_db
db.user=root
db.password=Qwe.123*
db.port=3306
db.host=localhost
db.url=jdbc:mysql://localhost:3306/minitienda_db?useSSL=false&serverTimezone=UTC
```

**Important**: Update the database credentials according to your MySQL setup.

### Maven Configuration
The project uses Maven with the following key configurations in `pom.xml`:
- **Java Version**: 21
- **MySQL Connector**: 8.0.33
- **Encoding**: UTF-8
- **Main Class**: `com.mycompany.hu3.HU3`

## 🧪 Testing

### Manual Testing
The application includes comprehensive input validation and error handling. Test the following scenarios:

1. **Valid Operations**:
   - Add products with valid data
   - Update existing products
   - Delete existing products
   - Search for products

2. **Error Handling**:
   - Try adding products with empty names
   - Try adding products with negative prices or stock
   - Try updating non-existent products
   - Try adding duplicate product names

3. **Database Connectivity**:
   - Verify the application handles database connection issues gracefully
   - Test with correct and incorrect database credentials

## 🚨 Troubleshooting

### Common Issues and Solutions

#### 1. Database Connection Error
**Error**: `SQLException: Access denied for user`
**Solution**: 
- Check your MySQL credentials in `application.properties`
- Ensure MySQL server is running
- Verify the database `minitienda_db` exists

#### 2. ClassNotFoundException: MySQL Driver
**Error**: `ClassNotFoundException: com.mysql.cj.jdbc.Driver`
**Solution**: 
- Run `mvn clean install` to download dependencies
- Check that MySQL Connector/J is in your classpath

#### 3. Port Already in Use
**Error**: Database connection issues
**Solution**: 
- Check if MySQL is running on the specified port (default 3306)
- Update the port in `application.properties` if necessary

#### 4. Maven Build Failure
**Error**: Maven compilation errors
**Solution**: 
- Ensure you have JDK 21 installed
- Run `mvn clean compile` to rebuild
- Check that `JAVA_HOME` is properly configured

#### 5. GUI Not Displaying
**Error**: Application runs but no GUI appears
**Solution**: 
- Ensure you're not running in a headless environment
- Check that your system supports Java Swing applications

## 🏛️ Architecture Overview

### Layered Architecture
The application follows a layered architecture pattern:

1. **Presentation Layer** (`ui` package): Handles user interface using Java Swing
2. **Service Layer** (`service` package): Contains business logic and validation
3. **Repository Layer** (`repository` package): Handles data access and persistence
4. **Domain Layer** (`domain` package): Contains entity models
5. **Infrastructure Layer** (`infra`, `dbconnection` packages): Configuration and database utilities

### Key Design Patterns
- **Repository Pattern**: Abstracts data access logic
- **Service Layer Pattern**: Encapsulates business rules
- **Dependency Injection**: Manual DI for loose coupling
- **Template Method**: Consistent error handling across layers

## 📈 Future Enhancements

Potential improvements for the application:

- **Web Interface**: Convert from Swing to a web-based interface
- **Authentication**: Add user login and role-based access
- **Advanced Search**: Multiple search criteria and filters
- **Reporting**: Generate inventory reports and analytics
- **Audit Trail**: Track all changes with timestamps and user information
- **Backup/Restore**: Database backup and restore functionality
- **Unit Tests**: Comprehensive test coverage
- **Logging**: Proper logging with different log levels

## 📄 License

This project is created for educational purposes as part of a programming course. No specific license is applied.

## 👨‍💻 Author

**Joseph García** - Initial development and implementation

## 📞 Support

For any questions or issues related to this project:

1. Review the troubleshooting section above
2. Check that all prerequisites are properly installed
3. Verify database configuration and connectivity
4. Ensure all dependencies are correctly downloaded via Maven

---

**Note**: This application is designed for educational purposes and demonstrates fundamental concepts in Java application development, database connectivity, and user interface design using Swing.