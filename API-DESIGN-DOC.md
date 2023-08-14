
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

### 3. API Overview
   2.1 API Name
   2.2 API Description
   2.3 Base URL
   2.4 Authentication and Authorization
   2.5 Versioning Strategy

### 4. Endpoints
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

   [Repeat for each endpoint]

### 5. Data Models
   4.1 [Model Name]
      4.1.1 Description
      4.1.2 Properties
      4.1.3 Example

   [Repeat for each data model]

### 6. Error Handling
   5.1 Error Response Format
   5.2 Common Error Codes
   5.3 Error Code Examples

### 7. Rate Limiting
   6.1 Rate Limiting Strategy
   6.2 Rate Limit Headers

### 8. Security
   7.1 Authentication
   7.2 Authorization
   7.3 API Keys
   7.4 OAuth Integration

### 9. Best Practices
   8.1 Naming Conventions
   8.2 Request and Response Formats
   8.3 Pagination
   8.4 Filtering and Sorting
   8.5 Error Handling
   8.6 Versioning

1### 0. Sample Requests and Responses
   9.1 [Endpoint Name]
      9.1.1 Sample Request
      9.1.2 Sample Response

   [Repeat for each endpoint]

### 11. Change Log
    [Version Number] - [Date]
    - Description of changes made in this version.

### 12. Conclusion
    Summarize the key points of the document and reiterate its importance.


