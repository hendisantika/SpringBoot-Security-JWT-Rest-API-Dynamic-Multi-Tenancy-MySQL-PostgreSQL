# SpringBoot-Security-JWT-Rest-API-Dynamic-Multi-Tenancy-MySQL-PostgreSQL
### Purpose

I wanted a solution where multi-tenancy is achieved by having a database per-tenant and all user information (username, password, client Id, etc.) for authentication and authorization stored in a user table in the respective tenant databases. This means that not only did I need a multi-tenant application, but also a secure application like any other web application secured by Spring Security.

I know how to use Spring Security to secure a web application and how to use Hibernate to connect to a database. The requirement further dictates that all users belonging to a tenant need to be stored in the tenant database and not a separate or central database. This would allow for complete data isolation for each tenant.

### Goal

* Archive Application SaaS Model client wise different database.
* Focus Spring Security and JWT
* You can connect multiple schemas with a single database, like MySQL — testdb, testdb2.
* You can connect multiple databases, like MySQL, PostgreSQL, or Oracle.

### What Is Multi-Tenancy?

Multi-tenancy is an architecture in which a single instance of a software application serves multiple customers. Each client is called a tenant. Tenants may be given the ability to customize some parts of the application.

A multi-tenant application is where a tenant (i.e. users in a company) feels that the application has been created and deployed for them. In reality, there are many such tenants, and they too are using the same application but get a feeling that it's built just for them.

Dynamic Multi-Tenant High-Level Diagram:

--- ADA DIAGRAM ---

Here,
* Client requests to login to the system.
* The system checks with the master database using client Id.
* If it's successful, set the current database to context based on the driver class name.
* If this fails, the user gets the message, "unauthorized".
* After successful authentication, the user gets a JWT for the next execution.

The whole process executes in the following workflow:

Technology and Project Structure:
* Java 11.
* Spring Boot.
* Spring Security.
* Spring AOP.
* Spring Data JPA.
* Hibernate.
* JWT.
* MySQ & PostgreSQL.
* IntelliJ IDEA Ultimate (2020.1).

### MySQL Database
Now, Create a Master Database and a tenant database.

Master Database:

In the master database, we only have one table (tbl_tenant_master), where all tenant information is storeed in the table.
MySQL 

```sql
DROP TABLE IF EXISTS `tbl_tenant_master`;
CREATE TABLE `tbl_tenant_master` (
  `tenant_client_id` int(10) unsigned NOT NULL,
  `db_name` varchar(50) NOT NULL,
  `url` varchar(250) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `driver_class` varchar(100) NOT NULL,
  `status` varchar(10) NOT NULL,
  PRIMARY KEY (`tenant_client_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `tbl_tenant_master` (`tenant_client_id`, `db_name`, `url`, `user_name`, `password`, `driver_class`, `status`) VALUES
('100', 'tenant_db', 'jdbc:mysql://localhost:3306/tenant_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Jakarta&useSSL=false', 'root', 'root', 'com.mysql.cj.jdbc.Driver', 'ACTIVE'),
('200', 'tenant_db_pgs', 'jdbc:postgresql://localhost:5432/tenant_db_pgs', 'hendisantika', 'root', 'org.postgresql.Driver', 'ACTIVE'),
('300', 'tenant_db2', 'jdbc:mysql://localhost:3306/tenant_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Jakarta&useSSL=false', 'root', 'root', 'com.mysql.cj.jdbc.Driver', 'ACTIVE');

```


 