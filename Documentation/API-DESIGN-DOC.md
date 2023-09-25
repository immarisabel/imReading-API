
# imReading API
## API Design Document

```txt
Version: v.1.0
Date: TBA
Author: Marisabel Munoz
Email: projects@marisabel.nl
```

### 1. Introduction

### 1.1 Purpose

   Since I embarked on my reading journey, I've been on the hunt for methods to track my books. I've experimented with journals, blogs, apps, and even dabbled in the realm of social media. Yet, I never found a solid fit until I started updating my Goodreads account. It was then that I realized what had been missing all along. Beyond simply cataloging books—an ability most modern apps possess—I've yearned to document my experience. I want to capture my thoughts as I read, not just after the last page is turned.

   Certainly, crafting a final review holds its charm. But my true delight resides in the process itself—the raw reactions, thoughts, and ideas that emerge from the dance between my mind and the page. Perhaps this is why the present tense, "I am," resonates with me; it signifies the act unfolding in the moment. No more reading and moving on; instead, I read and relish each fragment of time.

   This app is my endeavor to remain anchored in the now, to be wholly present in the act of reading. It's my way of refraining from the rush to reach the last page just to notch up another title on the list. My aspiration is to capture the essence of reading through a humble micro-blog dedicated solely to books.

   This document represents my attempt to structure my thoughts, a blueprint to craft an API that can seamlessly integrate with any frontend language. (The original concept was realized through a Spring Boot Web App.) The aim is efficiency—to bring my vision to life while enabling me to cherish the unfolding journey of reading.

   #### 1.2 Scope   

   The scope of this API design document covers the following functionalities and components of the **imReading** app:

   ##### Book Management:

   - Adding books to the user's reading list.
   - Retrieving details of books, such as title, author, genre, and cover image.
   - Marking books as read/unread.
   - Removing books from the reading list.
   - Categorize books in shelves
   - Rating books after reading

   ##### Journal Entries:

   - Creating journal entries for books the user has read, organized per date and page.
   - Retrieving journal entries, including date, book details, and user reflections.
   - Editing and deleting journal entries.
   - Adding enjoyment level and mood on the logs/entries.
   - Calculating percentage of reading according to pages

   ##### Discovering Books:

   - Use Google Books API to find all the book's metadata
   - Recomendation on what to read next
   - Book randomizer from the TBR list for when you don't know what to read next
     
   ##### Reading Statistics:

   - Providing reading progress statistics for each book.
   - Displaying the user's reading history and trends.
   - - per year
     - lifetime
     - per genre
     - per amount of logs
     - enjoyment levels and moods
     - total pages read per (month, year, week)
     
   ##### API Security:

   - Implementing user authentication using API keys.
   - Ensuring secure access to user-specific data.

   #### The following functionalities are out of scope for this API design document:

   - Social sharing features.
   - E-commerce features (purchasing books).
   - Third-party integrations (e.g., connecting to external book databases).
   - User interface details (app screens, layouts, designs).
   
   #### 1.3 Audience
   
   - Primarily, the document caters to my personal use.
   - Additionally, it serves as a resource for my children as they embark on their reading endeavors.
   - Lastly, it accommodates hypothetical users, allowing me to engage with a practical, real-world context for practice.
     
   #### 1.4 Definitions, Acronyms, and Abbreviations

   - TBR : To be read
   - Reading : books in progress
   - Read : books finished
   - DNF : Do not finish books
   - logs : journal entries

### 2. API Overview

   #### 2.1 API Name
   
   imReadingAPI
   
   #### 2.2 API Description
   
   provides the ability to set up a reading micro-blog to keep track of the reading experience
   
   #### 2.3 Base URL
   
   ```url
   https://imreading.marisabel.nl/api/v1/
   ```
   
   #### 2.4 Authentication and Authorization

   Our API utilizes API keys for authentication and authorization purposes. This ensures that only authorized clients can access the API endpoints and perform actions based on their granted permissions.

   **Authentication**:
      
   To authenticate with our API, clients are required to include their unique API key in the request headers. The API key serves as a credential to verify the identity of the client making the request. Without a valid API key, access to the API will be denied.

   Clients should include the API key in the Authorization header of the HTTP request. Here's an example of how the header might look:

   ```vbnet
   Authorization: API-Key YOUR_API_KEY_HERE
   ```

   **Authorization**:

   Once authenticated, the API relies on role-based authorization to determine what actions a client is allowed to perform. Each client is assigned a specific role that defines the level of access they have within the API.

   Currently, our API supports the following user roles:

   - **Reader**: Basic access to read resources. Client will be using this for public display of data.
   - **Writer**: Full access to all resources, including read and write operations. Admin access will be granted for back end operations only. 
      
   By keeping these 2 roles separated, we make sure data is not accidentally corrupted by the browser.


   #### 2.5 Versioning Strategy

   As I work on this personal project, I understand the significance of versioning to ensure a smooth experience for users while allowing room for improvements. My versioning strategy ensures that the API remains compatible with existing integrations even as I make updates.
   
   **Versioning Format:**
 
   I've chosen a versioning format in the URL to make it clear which version of the API you're interacting with. The version number is included as a segment in the URL path. So, the base URL for the API includes the version number like this:
   
   ```url
   https://imreading.marisabel.nl/api/v1/
   ```

   **Versioning Changes:**
   
   My goal is to maintain backward compatibility whenever feasible. This means that minor enhancements and updates within a version won't disrupt existing integrations. However, as the project evolves, there might be instances where changes could potentially affect compatibility.

   In such scenarios, I'll create a new version of the API. Significant changes that might not be backward compatible will lead to a new version number. For instance, if I introduce new endpoints, make substantial changes to the response structure, or modify behaviors that could impact existing integrations, I'll increment the version number in the URL.
   
   **Handling Deprecated Versions:**
   
   When a new version of the API is introduced, the previous version(s) will still be accessible during a transition period. I'll provide adequate notice and documentation to assist users in transitioning to the latest version. 
   
   **Example:**
   
   Let's say you've been using version 1 of the API, and I release version 2. You'll be able to access version 1 at `https://imreading.marisabel.nl/api/v1/` for a designated time while transitioning to version 2, available at `https://imreading.marisabel.nl/api/v2/`.
   
   
### 3. Endpoints

>[!WARNING]
>These are not final. I will update them as I build the API.

`http://localhost:8080/swagger-ui/index.html`

| Endpoint Name                   | Description                  | Request URL                 | HTTP Method | Request Headers | Request Parameters | Request Body | Response Body | Response Codes          | Response Examples |
|---------------------------------|------------------------------|-----------------------------|-------------|-----------------|--------------------|--------------|---------------|-------------------------|-------------------|
| **Books Endpoints**             |                              |                             |             |                 |                    |              |               |                         |                   |
| Create a Book                   | Create a new book            | `POST /books`               | `POST`      |                 |                    | JSON         | JSON          | 201, 400                |                   |
| Get All Books                   | Get all books                | `GET /books`                | `GET`       |                 |                    |              | JSON Array    | 200                     |                   |
| Get Book by ISBN                | Get a book by ISBN           | `GET /books/{isbn}`         | `GET`       |                 | ISBN               |              | JSON Object   | 200, 404, 601           |                   |
| Update Book by ISBN             | Update a book by ISBN        | `PUT /books/{isbn}`         | `PUT`       |                 | ISBN               | JSON         | JSON Object   | 200, 400, 404, 601      |                   |
| Delete Book by ISBN             | Delete a book by ISBN        | `DELETE /books/{isbn}`      | `DELETE`    |                 | ISBN               |              |               | 204, 404, 601           |                   |
| **Shelves Endpoints**           |                              |                             |             |                 |                    |              |               |                         |                   |
| Create a Shelf                  | Create a new shelf           | `POST /shelves`             | `POST`      |                 |                    | JSON         | JSON          | 201, 400, 803           |                   |
| Get All Shelves                 | Get all shelves              | `GET /shelves`              | `GET`       |                 |                    |              | JSON Array    | 200                     |                   |
| Get Shelf by ID                 | Get a shelf by ID            | `GET /shelves/{id}`         | `GET`       |                 | ID                 |              | JSON Object   | 200, 404, 801           |                   |
| Update Shelf by ID              | Update a shelf by ID         | `PUT /shelves/{id}`         | `PUT`       |                 | ID                 | JSON         | JSON Object   | 200, 400, 404, 801, 803 |                   |
| Delete Shelf by ID              | Delete a shelf by ID         | `DELETE /shelves/{id}`      | `DELETE`    |                 | ID                 |              |               | 204, 404, 801           |                   |
| **Reading Data Endpoints**      |                              |                             |             |                 |                    |              |               |                         |                   |
| Create Reading Data Entry       | Create a new reading entry   | `POST /reading-data`        | `POST`      |                 |                    | JSON         | JSON          | 201, 400                |                   |
| Get All Reading Data            | Get all reading data entries | `GET /reading-data`         | `GET`       |                 |                    |              | JSON Array    | 200                     |                   |
| Get Reading Data Entry by ID    | Get a reading entry by ID    | `GET /reading-data/{id}`    | `GET`       |                 | ID                 |              | JSON Object   | 200, 404                |                   |
| Update Reading Data Entry by ID | Update a reading entry       | `PUT /reading-data/{id}`    | `PUT`       |                 | ID                 | JSON         | JSON Object   | 200, 400, 404           |                   |
| Delete Reading Data Entry by ID | Delete a reading entry       | `DELETE /reading-data/{id}` | `DELETE`    |                 | ID                 |              |               | 204, 404                |                   |
| **Logs Endpoints**              |                              |                             |             |                 |                    |              |               |                         |                   |
| Create a Log Entry              | Create a new log entry       | `POST /logs`                | `POST`      |                 |                    | JSON         | JSON          | 201, 400                |                   |
| Get All Log Entries             | Get all log entries          | `GET /logs`                 | `GET`       |                 |                    |              | JSON Array    | 200                     |                   |
| Get Log Entry by ID             | Get a log entry by ID        | `GET /logs/{id}`            | `GET`       |                 | ID                 |              | JSON Object   | 200, 404                |                   |
| Update Log Entry by ID          | Update a log entry by ID     | `PUT /logs/{id}`            | `PUT`       |                 | ID                 | JSON         | JSON Object   | 200, 400, 404           |                   |
| Delete Log Entry by ID          | Delete a log entry by ID     | `DELETE /logs/{id}`         | `DELETE`    |                 | ID                 |              |               | 204, 404                |                   |
| **Tags Endpoints**              |                              |                             |             |                 |                    |              |               |                         |                   |
| Create a Tag                    | Create a new tag             | `POST /tags`                | `POST`      |                 |                    | JSON         | JSON          | 201, 400                |                   |
| Get All Tags                    | Get all tags                 | `GET /tags`                 | `GET`       |                 |                    |              | JSON Array    | 200                     |                   |
| Get Tag by ID                   | Get a tag by ID              | `GET /tags/{id}`            | `GET`       |                 | ID                 |              | JSON Object   | 200, 404                |                   |
| Update Tag by ID                | Update a tag by ID           | `PUT /tags/{id}`            | `PUT`       |                 | ID                 | JSON         | JSON Object   | 200, 400, 404           |                   |
| Delete Tag by ID                | Delete a tag by ID           | `DELETE /tags/{id}`         | `DELETE`    |                 | ID                 |              |               | 204, 404                |                   |


>[!NOTE]
>template for later on to document each endpoint:
>    3.1 [Endpoint Name]
>    3.1.1 Description
>     3.1.2 Request URL
>     3.1.3 HTTP Method
>     3.1.4 Request Headers
>     3.1.5 Request Parameters
>     3.1.6 Request Body
>     3.1.7 Response Body
>     3.1.8 Response Codes
>     3.1.9 Response Examples



### 4. Data Models
[Database Design
](https://dbdocs.io/marisabelmunoz24/imReading-database-design)

**password**: ee;?<UGsR5n

![](https://github.com/immarisabel/imReading-books-tracker/blob/main/Documentation/books.png.png?raw=true)

For details about the fields see the [DBDocs.io page
](https://dbdocs.io/marisabelmunoz24/imReading-database-design).

#### 4.1 Book

   **4.1.1 Description**
      
      The "Book" entity represents the metadata about a book.
      
   **4.1.2 Example**
 
   ```java

     @Table(name = "books")
       @Id
       private String isbn;
       @NotNull
       private String title;
       @NotNull
       private String author;
       @NotNull
       private int pages;
       private String thumbnailUrl;
   
   // SHELVES
       @ManyToMany(mappedBy = "books")
       private List<Shelves> shelf;
   
}

 ```

#### 4.2 Reading_Data

   **4.2.1 Description**
      
      The "Reading Data" entity represents the metadata about the reading process. Not all books will contain this data. Every Reading Data needs to contain a ISBN book.
      
   **4.2.2 Example**

```java
     @Table(name = "reading_data")
       @Id
       @GeneratedValue (strategy = GenerationType.IDENTITY)
       private int id;
       @NotNull
       @ManyToOne
       @JoinColumn(name = "books_isbn", referencedColumnName = "isbn")
       private Books book;
       private Date startedDate;
       private Date finishedDate;
       @NotNull
       private String status;
       private int currentPage;
       private int rating;
       @NotNull
       private boolean favorite;
}
```

#### 4.3 Shelves

   **4.3.1 Description**
      
      The "Shelves" entity represents the classification of the book according to the reader's preferences.
      
   **4.3.2 Example**
```java
     @Table(name = "shelves")
       @Id
       @GeneratedValue (strategy = GenerationType.IDENTITY)
       private int id;
       @NotNull
       private String name;
       @ManyToMany
       @JoinTable(
        name = "shelved_books",
        joinColumns = @JoinColumn(name = "shelves_shelf_id"),
        inverseJoinColumns = @JoinColumn(name = "books_isbn"))
       private List<Books> books;
}
```
#### 4.4 Tags

   **4.4.1 Description**
      
      The "Tags" entity represents the sub-classification of the book according to the reader's preferences. These are to be used for experience meassurement, NOT classification of books. Though the user is free to use them as such, this won't reflect their experience on the statistics.
      
   **4.4.2 Example**
```java
     @Table(name = "tags")
       @Id
       @GeneratedValue (strategy = GenerationType.IDENTITY)
       private int id;
       @NotNull
       private String name;
}
```

#### 4.5 Logs

   **4.5.1 Description**
      
      The "Logs" entity represents the written experience of the reading process. This can be quotes, notes, thoughts and more.
      
   **4.5.2 Example**
```java
     @Table(name = "logs")
       @Id
       @GeneratedValue (strategy = GenerationType.IDENTITY)
       private int id;
       @NotNull
       @ManyToOne
       @JoinColumn(name = "books_isbn", referencedColumnName = "isbn")
       private Books book;
       @NotNull
       @Temporal(TemporalType.TIMESTAMP)
       private Date date;
       @Column(columnDefinition = "TEXT")
       private String content
       private String mood;
       @ManyToMany
       @JoinTable(name = "logs_tags",
            joinColumns = @JoinColumn(name = "logs_id"),
            inverseJoinColumns = @JoinColumn(name = "tags_id"))
       private List<Tags> tags;


}
```

### 5. Response Codes

#### Standard Errors

| HTTP Status Code | Description                            | Example Response Body                                       |
|------------------|----------------------------------------|-------------------------------------------------------------|
| 200              | OK                                     | `{ "message": "Request successful" }`                       |
| 201              | Created                                | `{ "id": 123, "name": "New Item" }`                         |
| 204              | No Content                             |                                                             |
| 400              | Bad Request                            | `{ "error": "Invalid input" }`                             |
| 401              | Unauthorized                           | `{ "error": "Authentication required" }`                   |
| 403              | Forbidden                              | `{ "error": "Permission denied" }`                         |
| 404              | Not Found                              | `{ "error": "Resource not found" }`                        |
| 422              | Unprocessable Entity                   | `{ "error": "Validation failed" }`                         |
| 500              | Internal Server Error                  | `{ "error": "Internal server error" }`                     |

#### Global Errors (6XX)

| Error Code | Description             | Example Response Body                                                       |
|------------|-------------------------|-----------------------------------------------------------------------------|
| 601        | No books found          | `{ "error": "No books found with the provided ISBN" }`                      |
| 602        | ID Not Found            | `{ "error": "No matching ID found" }`                                       |
| 603        | Nothing Found with ISBN | `{ "error": "Nothing found with the provided ISBN" }`                       |

#### Books (70X) & Log Errors (71X)

| Error Code | Description                   | Example Response Body                                               |
|------------|-------------------------------|---------------------------------------------------------------------|
| 701   ✔    | Empty book list               | `{ "error": "No books found in database" }`                         |
| 703   ✔    | Duplicate Book Exception      | `{ "error": "There is already a book with ISBN in the database" }`  |
| 711   ✔    | Pages exceed the book's pages | `{ "error": "Your current page is greater than the book's pages" }` | 

#### Shelves & Tags Errors (8XX)

| Error Code | Description        | Example Response Body                                      |
|---------|--------------------|------------------------------------------------------------|
| 801     | Shelf Not Found    | `{ "error": "Shelf not found with the provided ID" }`      |
| 802     | Invalid Shelf Name | `{ "error": "Invalid shelf name format" }`                 |
| 803     | Duplicate Shelf    | `{ "error": "A shelf with the same name already exists" }` |

#### Reading Data Errors (9XX) & Reviews (91X)

| Error Code | Description                 | Example Response Body                                    |
|------------|-----------------------------|----------------------------------------------------------|
| 901        | No Reading Data Found       | `{ "error": "No reading data found for ISBN: isbn" }`    |
| 902        | Missing Required Fields     | `{ "error": "Missing required fields in log data" }`     |
| 903        | Book Already Marked as Read | `{ "error": "This book is already marked as read" }`     |
| 911        | No Review Found             | `{ "error": "No review found for ISBN: isbn" }`          |
| 912        | Review Exist Already        | `{ "error": "A review for ISBN: isbn, already exist." }` |


### 6. Security

In this section, we cover the various aspects of securing access to the API and ensuring that only authorized users can interact with the resources.

#### 6.1 Authentication

Authentication is the process of verifying the identity of a client making a request to the API. It ensures that only legitimate users can access the resources. We use **Token-based Authentication** for our API.

To authenticate a request, clients must include an `Authorization` header with a bearer token in the format: `Bearer <token>`. Tokens can be obtained by registering an account on our platform and generating an authentication token through the user settings.

#### 6.2 Authorization

Authorization determines the permissions and actions a user can perform once they're authenticated. Our API implements a **Role-Based Access Control (RBAC)** system.

Each user is assigned a role (e.g., user, admin) that defines their level of access. The endpoints and actions accessible to a user depend on their role. Make sure to include the appropriate role in the request to access specific resources.

#### 6.3 API Keys

API keys are used to identify the calling application or user and provide a level of security when making requests to the API. While API keys are less secure than tokens, they are suitable for certain use cases.

To include an API key in your request, add an `api_key` parameter to the query string or include it in the request headers under the `X-API-Key` field.

#### 6.4 OAuth Integration

We support OAuth 2.0 for third-party application integration and user authentication. OAuth allows applications to securely access user data without exposing their credentials.

To integrate with our API using OAuth, you'll need to register your application and obtain a client ID and secret. Once authorized by the user, your application will receive an access token, which you can use to make authorized requests on behalf of the user.

By providing multiple authentication and authorization options, we ensure that you can choose the method that best suits your application's needs while maintaining the security of your data.

### 7. Best Practices

As I pursue the creation of a robust and user-friendly API for reading logs and book tracking, I've incorporated the following best practices to enhance the consistency, usability, and maintainability of the system.

#### 7.1 Naming Conventions

I believe that using consistent naming conventions is important for easy understanding and clear code. I stick to a camelCase naming style for endpoints, resources, query parameters, and request/response fields. This makes sure that the different parts of the API are easy to understand and follow industry norms.

#### 7.2 Request and Response Formats

I've kept the request and response formats uniform, which makes it easier for clients to integrate with the API. I've chosen to use the JSON format for both requests and responses. This simplifies how data is managed and ensures compatibility across different platforms.

#### 7.3 Pagination

When I work with large sets of data, I use pagination to ensure smooth performance and responsiveness. For instance, when fetching potentially lengthy lists like book records, I split them into manageable pages to avoid overwhelming clients with too much information. I've included pagination details in the response headers, so clients know how to navigate through these larger result sets.

#### 7.4 Filtering and Sorting

To make data retrieval more efficient, the API lets clients filter and sort data. Clients can specify criteria and sorting preferences using query parameters. This helps users narrow down results and organize data as they prefer.

#### 7.5 Error Handling

Maintaining a seamless user experience relies on robust error handling. The API uses clear and standard error responses, including proper HTTP status codes and detailed error messages. This helps clients easily spot issues and troubleshoot, leading to quick problem resolution.

#### 7.6 Versioning

Versioning is crucial for keeping the API compatible and handling changes as it evolves. We handle versioning by updating the URL path. As needed, new API versions will be introduced, while keeping older versions available. This ensures a smooth transition for clients.

By adhering to these practices, my aim is to provide an API that's functional, efficient, well-structured, user-friendly, and adaptable to diverse user needs.

### 11. Conclusion

This document is presently under development, representing the foundation from which I will embark on refining my API. Notably, Sections 6 and 8 will demand particular attention, requiring adjustments throughout the API's evolution. Given my primary objective of personal utilization, I am approaching this development with the cognizance that it will extend its utility to others, much like the WordPress model—facilitating individual installation and tailored configuration. This two-fold strategy highlights my dedication to developing an API that addresses my individual requirements while also simulating real-world application scenarios, thereby facilitating my learning process.

