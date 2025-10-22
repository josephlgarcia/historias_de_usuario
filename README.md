# HU4 - Inventory Management System

A Java-based inventory management application built with Maven, featuring a graphical user interface for product management operations with MySQL database integration.

## 📋 Table of Contents

- [Description](#📖-description)
- [Features](#✨-features)
- [Technologies Used](#🛠-Technologies-Used)
- [Architecture](#🏗-architecture)
- [Prerequisites](#📋-prerequisites)
- [Installation](#🚀-installation)
- [Database Setup](#🗄-database-setup)
- [Configuration](#⚙️-configuration)
- [Running the Application](#▶️-running-the-application)
- [Usage](#📖-usage)
- [Project Structure](#📁-project-structure)
- [Exception Handling](#🚨-exception-handling)
- [Contributing](#🤝-contributing)
- [License](#📄-license)

## 📖 Description

HU4 is a comprehensive inventory management system designed to help small businesses manage their product catalog. The application provides a user-friendly graphical interface for performing CRUD (Create, Read, Update, Delete) operations on products, with robust data validation and error handling.

The system tracks essential product information including name, price, and stock levels, while maintaining data integrity through comprehensive validation rules and custom exception handling.

## ✨ Features

- **Product Management**:
  - Add new products with validation
  - View complete product inventory
  - Update product prices and stock levels
  - Delete products with confirmation
  - Search products by name (partial matching)

- **Data Validation**:
  - Required field validation
  - Price validation (must be positive)
  - Stock validation (cannot be negative)
  - Duplicate name prevention
  - Input format validation

- **User Interface**:
  - Intuitive GUI using Java Swing/JOptionPane
  - Formatted product listings with scrollable views
  - Operation counters and session summary
  - Comprehensive error messaging

- **Database Integration**:
  - MySQL database connectivity
  - Connection pooling and management
  - SQL injection protection via prepared statements
  - Transaction handling

## 🛠 Technologies Used

- **Java 21** - Core programming language
- **Maven** - Dependency management and build tool
- **MySQL 8.0.33** - Database management system
- **JDBC** - Database connectivity
- **Java Swing** - Graphical user interface
- **MySQL Connector/J** - Database driver

## 🏗 Architecture

The application follows a **layered architecture** with clear separation of concerns:

```
┌─────────────────┐
│   Presentation  │ ← UI Layer (InitUI.java)
│     Layer       │
├─────────────────┤
│   Service       │ ← Business Logic Layer
│     Layer       │   (ProductService, ProductServiceImpl)
├─────────────────┤
│  Repository     │ ← Data Access Layer
│     Layer       │   (ProductRepository, ProductRepositoryImpl)
├─────────────────┤
│   Database      │ ← Data Storage Layer
│     Layer       │   (MySQL Database)
└─────────────────┘
```

### Design Patterns Implemented:

- **Repository Pattern**: Abstracts data access logic
- **Service Pattern**: Encapsulates business logic
- **Dependency Injection**: Manages object dependencies
- **DAO Pattern**: Data Access Object implementation

## 📋 Prerequisites

Before running the application, ensure you have the following installed:

- **Java Development Kit (JDK) 21** or later
- **Apache Maven 3.6+**
- **MySQL Server 8.0+**
- **MySQL Client** or **MySQL Workbench** (for database setup)

### System Requirements

- **Operating System**: Windows, macOS, or Linux
- **Memory**: Minimum 512MB RAM
- **Disk Space**: 100MB free space
- **Network**: MySQL server connection

## 🚀 Installation

1. **Clone or download the project** to your local machine:
   ```bash
   # If using git
   git clone <repository-url>
   cd HU4
   
   # Or extract if downloaded as ZIP
   unzip HU4.zip
   cd HU4
   ```

2. **Verify Java installation**:
   ```bash
   java -version
   # Should show Java 21 or later
   ```

3. **Verify Maven installation**:
   ```bash
   mvn -version
   # Should show Maven 3.6+ and Java 21
   ```

## 🗄 Database Setup

1. **Start MySQL Server** and connect as root or admin user.

2. **Create the database** using the provided schema:
   ```bash
   mysql -u root -p < src/main/java/com/mycompany/hu4/databaseSchema.sql
   ```
   
   Or manually execute the SQL commands:
   ```sql
   DROP DATABASE IF EXISTS minitienda;
   CREATE DATABASE minitienda CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   USE minitienda;
   
   CREATE TABLE products (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(60) NOT NULL UNIQUE,
       stock INT NOT NULL,
       price DOUBLE NOT NULL
   );
   ```

3. **Create a database user** (recommended):
   ```sql
   CREATE USER 'inventory_user'@'localhost' IDENTIFIED BY 'your_password';
   GRANT ALL PRIVILEGES ON minitienda.* TO 'inventory_user'@'localhost';
   FLUSH PRIVILEGES;
   ```

## ⚙️ Configuration

Configure the database connection in `src/main/resources/application.properties`:

```properties
db.vendor=mysql
db.name=minitienda
db.user=your_username
db.password=your_password
db.port=3306
db.host=localhost
db.url=jdbc:mysql://localhost:3306/minitienda?useSSL=false&serverTimezone=UTC
```

**Important**: Update the `db.user` and `db.password` fields with your MySQL credentials before running the application.

## ▶️ Running the Application

### Method 1: Using Maven (Recommended)

1. **Navigate to the project directory**:
   ```bash
   cd /path/to/HU4
   ```

2. **Clean and compile the project**:
   ```bash
   mvn clean compile
   ```

3. **Run the application**:
   ```bash
   mvn exec:java -Dexec.mainClass="com.mycompany.hu4.HU4"
   ```

### Method 2: Using NetBeans IDE

1. Open NetBeans IDE
2. File → Open Project → Select the HU4 folder
3. Right-click on the project → Run

### Method 3: Using Compiled JAR

1. **Build the JAR file**:
   ```bash
   mvn clean package
   ```

2. **Run the JAR file**:
   ```bash
   java -jar target/HU4-1.0-SNAPSHOT.jar
   ```

## 📖 Usage

Once the application starts, you'll see a menu with the following options:

### 1. Add Product
- Enter product name (required, must be unique)
- Enter price (required, must be positive)
- Enter initial stock (required, cannot be negative)

### 2. List Inventory
- Displays all products in a formatted table
- Shows ID, name, price, and stock for each product
- Scrollable view for large inventories

### 3. Update Price
- Enter product ID to locate the product
- View current product information
- Enter new price (must be positive)

### 4. Update Stock
- Enter product ID to locate the product
- View current stock level
- Enter new stock amount (cannot be negative)

### 5. Delete Product
- Enter product ID to locate the product
- Confirm deletion with detailed product information
- Product is permanently removed from inventory

### 6. Search by Name
- Enter partial or complete product name
- View all matching products
- Case-insensitive search

### 7. Exit
- Shows session summary with operation counts
- Safely closes the application

## 📁 Project Structure

```
HU4/
├── pom.xml                                    # Maven configuration
├── src/
│   └── main/
│       ├── java/com/mycompany/hu4/
│       │   ├── HU4.java                       # Main application class
│       │   ├── domain/
│       │   │   └── Product.java               # Product entity/model
│       │   ├── ui/
│       │   │   └── InitUI.java               # User interface logic
│       │   ├── service/
│       │   │   ├── ProductService.java        # Service interface
│       │   │   └── impl/
│       │   │       └── ProductServiceImpl.java # Business logic implementation
│       │   ├── repository/
│       │   │   ├── Repository.java            # Generic repository interface
│       │   │   ├── ProductRepository.java     # Product-specific repository interface
│       │   │   └── impl/
│       │   │       └── ProductRepositoryImpl.java # Data access implementation
│       │   ├── dbconnection/
│       │   │   └── DbConnection.java          # Database connection management
│       │   ├── infra/config/
│       │   │   └── AppConfig.java             # Configuration management
│       │   ├── exception/                     # Custom exception classes
│       │   │   ├── AppException.java          # Base exception class
│       │   │   ├── CampoVacioException.java   # Empty field exception
│       │   │   ├── ConexionException.java     # Database connection exception
│       │   │   ├── DatoInvalidoException.java # Invalid data exception
│       │   │   ├── DuplicadoException.java    # Duplicate entry exception
│       │   │   ├── EntidadNoEncontradaException.java # Entity not found exception
│       │   │   ├── OperacionNoPermitidaException.java # Operation not allowed exception
│       │   │   ├── PersistenciaException.java # Database persistence exception
│       │   │   ├── PrecioInvalidoException.java # Invalid price exception
│       │   │   ├── StockInsuficienteException.java # Insufficient stock exception
│       │   │   └── StockInvalidoException.java # Invalid stock exception
│       │   └── databaseSchema.sql             # Database schema definition
│       └── resources/
│           └── application.properties         # Database configuration
└── target/                                    # Compiled classes and JAR file
```

## 🚨 Exception Handling

The application implements comprehensive error handling with custom exceptions:

| Exception | Description | User Impact |
|-----------|-------------|-------------|
| `CampoVacioException` | Required fields are empty | Clear field-specific error messages |
| `PrecioInvalidoException` | Price is zero or negative | Validation error with correction guidance |
| `StockInvalidoException` | Stock is negative | Prevents invalid inventory levels |
| `DuplicadoException` | Product name already exists | Prevents duplicate entries |
| `EntidadNoEncontradaException` | Product not found by ID | User-friendly "not found" messages |
| `ConexionException` | Database connectivity issues | Database connection error handling |
| `PersistenciaException` | Database operation failures | Data persistence error management |

## 🔧 Troubleshooting

### Common Issues

1. **Database Connection Error**:
   - Verify MySQL server is running
   - Check credentials in `application.properties`
   - Ensure database `minitienda` exists
   - Verify firewall settings

2. **Compilation Errors**:
   - Ensure Java 21+ is installed and configured
   - Run `mvn clean compile` to refresh dependencies
   - Check Maven installation with `mvn -version`

3. **Missing Dependencies**:
   - Run `mvn clean install` to download all dependencies
   - Check internet connection for Maven repository access

4. **GUI Not Displaying**:
   - Ensure you're not running in headless mode
   - Check display/X11 settings on Linux systems
   - Verify Java Swing support is available

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is created for educational purposes. Please check with the original author for license information.

---

## 📞 Support

If you encounter any issues or have questions about the application:

1. Check the troubleshooting section above
2. Verify your environment meets all prerequisites
3. Ensure database configuration is correct
4. Check application logs for detailed error information

**Note**: This application is designed for educational and small business use. For production environments, consider additional security measures and performance optimizations.