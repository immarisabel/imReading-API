
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
   3.1 [Endpoint Name]
      3.1.1 Description
      3.1.2 Request URL
      3.1.3 HTTP Method
      3.1.4 Request Headers
      3.1.5 Request Parameters
      3.1.6 Request Body
      3.1.7 Response Body
      3.1.8 Response Codes
      3.1.9 Response Examples



### 4. Data Models
[Database Design
](https://dbdocs.io/marisabelmunoz24/imReading-database-design)

**password**: ee;?<UGsR5n

#### 4.1 Book

   **4.1.1 Description**
      
      The "Book" entity represents the metadata about a book.
      
   **4.1.2 Properties**

   - `id`: Unique identifier for the book. (Type: Long)
   - `title`: Title of the book. (Type: String)
   - `author`: Author of the book. (Type: String)
   - `isbn`: International Standard Book Number for the book. (Type: String)
   - `thumbnailUrl`: url to the book's cover (Type: String)
   - `page`: the number of pages in a book (Type: int)
   - `reading`: this is the object which will store the reading data: status, dates, pages, bookmarks, quotes logs, moods, etc. (Type: Reading)
   - `shelves`: the categories it belongs to  (Type: List<Shelf>)
   - `tags`: anything you want to add as part of the experience to later find them back but not necessarily shelving.  (Type: List<Tag>)
   - `logs`: the object for storing the reading logs
  
   **4.1.3 Example**
 
   ```java

   @Getter
   @Setter
   @ToString
   @AllArgsConstructor
   @NoArgsConstructor
   @EqualsAndHashCode
   @Entity
     @Table(name = "books")
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private int id;
       private String title;
       private String author;
       private String isbn;
       private String thumbnailUrl;
       private int page;

       @OneToOne(mappedBy = "book")
       private ReadingData readingData;
   
       @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
       @JoinTable(
               name = "shelved_books",
               joinColumns = @JoinColumn(name = "books_id"),
               inverseJoinColumns = @JoinColumn(name = "shelves_id"))
       private Set<ShelvesEntity> shelves = new HashSet<>();
   
       @OneToMany(mappedBy = "bookId", cascade = CascadeType.ALL, orphanRemoval = true)
       private List<LogEntity> logs = new ArrayList<>(); // TO MOVE TO READING OBJECT

}

 ```

---
  
** 👉 WORKING HERE:
 
 WIP Data Model Objects: 
   - ReadingData
   - - bookid
     - date started
     - date finished
     - current page
     - status
     - rating
     - logs obj
   - Logs
   - - id
     - date
     - page
     - content
     - mood
   - Shelves
   - - id
     - shelf name
   - Tags
   - - id
     - tag name**
    
   ---

   4.1 [Model Name]
      4.1.1 Description
      4.1.2 Properties
      4.1.3 Example


### 5. Error Handling
   5.1 Error Response Format
   5.2 Common Error Codes
   5.3 Error Code Examples

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

