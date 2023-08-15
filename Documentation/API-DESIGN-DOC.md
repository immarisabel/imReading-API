
# imReading API
## API Design Document

```txt
Version: v.1.0
Date: TBA
Author: Marisabel Munoz
Email: projects@marisabel.nl
```

### 1. Introduction
   
   #### 1.1 Purpose

   Ever since I begun to read I have been looking for ways to track my books. I have used journals, blogs and apps. Also social media. I never quite stuck anywhere until I begun writing updates on my GoodReads account. I realized what I've been missing. Appart from tracking books, something most apps can do nowadays, I wnat to log my experience.
My thoughts as I read, not after I finish. Yes, a final review is nice. But I enjoy the process, my reactions, my thoughts and ideas floating from the reflection of the page. I guess that is why I love the present tense of "I am", it means I am doing it on the moment. I did not read and forgot about it. I am reading it and savoring each moment.

   This app is an attempt to keep me present in the moment, present in just being, on the reading journey. Not wanting to just finish fast so I can rate and add another counted book to my list. My goal, to gather the experience of reading through a simple micro-blog dedicated to books. This document is my way to organize my ideas in order to create this API in the most efficient way, so I can use it with any frontend language. (Original idea was implemented in Spring Boot Web App)

   #### 1.2 Scope   

   The scope of this API design document covers the following functionalities and components of the **imReading** app:

   ##### Book Management:

   - Adding books to the user's reading list.
   - Retrieving details of books, such as title, author, genre, and cover image.
   - Marking books as read/unread.
   - Removing books from the reading list.
   - Categorize books in shelves

   ##### Journal Entries:

   - Creating journal entries for books the user has read, organized per date and page.
   - Retrieving journal entries, including date, book details, and user reflections.
   - Editing and deleting journal entries.
   - Adding enjoyment level and mood on the logs/entries.

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
     
   ##### API Security:

   - Implementing user authentication using API keys.
   - Ensuring secure access to user-specific data.

   #### The following functionalities are out of scope for this API design document:

   - Social sharing features.
   - E-commerce features (purchasing books).
   - Third-party integrations (e.g., connecting to external book databases).
   - User interface details (app screens, layouts, designs).
   
   #### 1.3 Audience
   
   - myself, who else? And maybe my kids. And friends. And who knows later on.
     
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

| Endpoint Name             | Description                 | Request URL                | HTTP Method | Request Headers | Request Parameters | Request Body | Response Body | Response Codes | Response Examples |
|---------------------------|-----------------------------|----------------------------|-------------|-----------------|-------------------|--------------|---------------|----------------|------------------|
| **Books Endpoints**       |                             |                            |             |                 |                   |              |               |                |                  |
| Create a Book             | Create a new book           | `POST /books`              | `POST`      |                 |                   | JSON         | JSON          | 201, 400       |                  |
| Get All Books             | Get all books               | `GET /books`               | `GET`       |                 |                   |              | JSON Array    | 200            |                  |
| Get Book by ISBN          | Get a book by ISBN          | `GET /books/{isbn}`        | `GET`       |                 | ISBN              |              | JSON Object   | 200, 404       |                  |
| Update Book by ISBN       | Update a book by ISBN       | `PUT /books/{isbn}`        | `PUT`       |                 | ISBN              | JSON         | JSON Object   | 200, 400, 404 |                  |
| Delete Book by ISBN       | Delete a book by ISBN       | `DELETE /books/{isbn}`     | `DELETE`    |                 | ISBN              |              |               | 204, 404       |                  |
| **Shelves Endpoints**     |                             |                            |             |                 |                   |              |               |                |                  |
| Create a Shelf            | Create a new shelf          | `POST /shelves`            | `POST`      |                 |                   | JSON         | JSON          | 201, 400       |                  |
| Get All Shelves           | Get all shelves             | `GET /shelves`             | `GET`       |                 |                   |              | JSON Array    | 200            |                  |
| Get Shelf by ID           | Get a shelf by ID           | `GET /shelves/{id}`        | `GET`       |                 | ID                |              | JSON Object   | 200, 404       |                  |
| Update Shelf by ID        | Update a shelf by ID        | `PUT /shelves/{id}`        | `PUT`       |                 | ID                | JSON         | JSON Object   | 200, 400, 404 |                  |
| Delete Shelf by ID        | Delete a shelf by ID        | `DELETE /shelves/{id}`     | `DELETE`    |                 | ID                |              |               | 204, 404       |                  |
| **Reading Data Endpoints**|                             |                            |             |                 |                   |              |               |                |                  |
| Create Reading Data Entry | Create a new reading entry  | `POST /reading-data`       | `POST`      |                 |                   | JSON         | JSON          | 201, 400       |                  |
| Get All Reading Data       | Get all reading data entries| `GET /reading-data`        | `GET`       |                 |                   |              | JSON Array    | 200            |                  |
| Get Reading Data Entry by ID | Get a reading entry by ID| `GET /reading-data/{id}`   | `GET`       |                 | ID                |              | JSON Object   | 200, 404       |                  |
| Update Reading Data Entry by ID | Update a reading entry | `PUT /reading-data/{id}`   | `PUT`       |                 | ID                | JSON         | JSON Object   | 200, 400, 404 |                  |
| Delete Reading Data Entry by ID | Delete a reading entry | `DELETE /reading-data/{id}`| `DELETE`    |                 | ID                |              |               | 204, 404       |                  |
| **Logs Endpoints**         |                             |                            |             |                 |                   |              |               |                |                  |
| Create a Log Entry        | Create a new log entry      | `POST /logs`               | `POST`      |                 |                   | JSON         | JSON          | 201, 400       |                  |
| Get All Log Entries        | Get all log entries         | `GET /logs`                | `GET`       |                 |                   |              | JSON Array    | 200            |                  |
| Get Log Entry by ID        | Get a log entry by ID       | `GET /logs/{id}`           | `GET`       |                 | ID                |              | JSON Object   | 200, 404       |                  |
| Update Log Entry by ID     | Update a log entry by ID    | `PUT /logs/{id}`           | `PUT`       |                 | ID                | JSON         | JSON Object   | 200, 400, 404 |                  |
| Delete Log Entry by ID     | Delete a log entry by ID    | `DELETE /logs/{id}`        | `DELETE`    |                 | ID                |              |               | 204, 404       |                  |
| **Tags Endpoints**         |                             |                            |             |                 |                   |              |               |                |                  |
| Create a Tag               | Create a new tag             | `POST /tags`               | `POST`      |                 |                   | JSON         | JSON          | 201, 400       |                  |
| Get All Tags               | Get all tags                 | `GET /tags`                | `GET`       |                 |                   |              | JSON Array    | 200            |                  |
| Get Tag by ID              | Get a tag by ID              | `GET /tags/{id}`           | `GET`       |                 | ID                |              | JSON Object   | 200, 404       |                  |
| Update Tag by ID           | Update a tag by ID           | `PUT /tags/{id}`           | `PUT`       |                 | ID                | JSON         | JSON Object   | 200, 400, 404 |                  |
| Delete Tag by ID           | Delete a tag by ID           | `DELETE /tags/{id}`        | `DELETE`    |                 | ID                |              |               | 204, 404       |                  |


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

   @Entity
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
 @Entity
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
 @Entity
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
 @Entity
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
 @Entity
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

#### Book Errors

| Error Code | Description                            | Example Response Body                                       |
|------------|----------------------------------------|-------------------------------------------------------------|
| 601        | No Book Found                          | `{ "error": "No book found with the provided ISBN" }`      |
| 602        | Invalid ISBN                           | `{ "error": "Invalid ISBN format" }`                       |

#### Log Errors

| Error Code | Description                            | Example Response Body                                       |
|------------|----------------------------------------|-------------------------------------------------------------|
| 603        | Duplicate Entry                        | `{ "error": "Log for this book and date already exists" }`|
| 604        | Missing Required Fields                | `{ "error": "Missing required fields in log data" }`       |
| 605        | Book Already Marked as Read            | `{ "error": "This book is already marked as read" }`       |
| 606        | Invalid Date Format                    | `{ "error": "Invalid date format, please use YYYY-MM-DD" }`|
| 607        | Log Not Found                          | `{ "error": "Reading log not found with the given ID" }`   |


#### Shelves Errors (7XX)

| Error Code | Description                                  | Example Response Body                                          |
|------------|----------------------------------------------|----------------------------------------------------------------|
| 701        | Shelf Not Found                           | `{ "error": "Shelf not found with the provided ID" }`        |
| 702        | Invalid Shelf Name                       | `{ "error": "Invalid shelf name format" }`                   |
| 703        | Duplicate Shelf                           | `{ "error": "A shelf with the same name already exists" }`   |

#### Reading Data Errors (8XX)

| Error Code | Description                                  | Example Response Body                                       |
|------------|----------------------------------------------|-------------------------------------------------------------|
| 801        | Duplicate Entry                        | `{ "error": "Log for this book and date already exists" }`  |
| 802        | Missing Required Fields                | `{ "error": "Missing required fields in log data" }`       |
| 803        | Book Already Marked as Read        | `{ "error": "This book is already marked as read" }`       |
| 804        | Invalid Date Format                    | `{ "error": "Invalid date format, please use YYYY-MM-DD" }`|
| 805        | Log Not Found    



### 6. Rate Limiting
   6.1 Rate Limiting Strategy
   6.2 Rate Limit Headers

### 7. Security
   7.1 Authentication
   7.2 Authorization
   7.3 API Keys
   7.4 OAuth Integration

### 8. Best Practices
   8.1 Naming Conventions
   8.2 Request and Response Formats
   8.3 Pagination
   8.4 Filtering and Sorting
   8.5 Error Handling
   8.6 Versioning

#### 9. Sample Requests and Responses
   9.1 [Endpoint Name]
      9.1.1 Sample Request
      9.1.2 Sample Response

   [Repeat for each endpoint]

### 10. Change Log
    [Version Number] - [Date]
    - Description of changes made in this version.

### 11. Conclusion
    Summarize the key points of the document and reiterate its importance.


