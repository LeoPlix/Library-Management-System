# Library Management System — Project Specification

## Project Goal
Develop a complete system for managing a library collection. The system must allow operations such as searching works, registering users, registering works, and registering loans (requests), among others.

---

## Concepts and Relationships

The model revolves around four main concepts:

- Creators
- Works
- Users
- Requests (Loans)

Additional entities may exist depending on the chosen implementation.

---

## Creators
A creator represents an author or director.

Each creator:
- Has a name used as a unique identifier in the application.
- Maintains an up-to-date list of works they created.
- Must be removed from the system if they no longer have any works.

The implementation should allow adding properties to creators without impacting existing code.

---

## Works
The system keeps a set of works. Each work is identified by:
- Work number (automatic, incremental)
- Title
- Price (integer)
- Category
- Number of available copies

Initial categories:
- Reference works
- Fiction works
- Technical and scientific works

The application should make it easy to create new categories.

Initial supported work types:
- Books
  - Authors (one or more creators)
  - ISBN (10 or 13 characters)
- DVDs
  - Director (one creator)
  - IGAC registration number

The architecture should allow adding new work types (e.g., CDs, VHS) with minimal impact.

---

## Users
Each user has:
- User number (automatic, incremental)
- Name
- Email address
- Status (active or suspended)

A user becomes suspended if they return works late, and remains suspended until they return the overdue works and pay all fines.

User classifications (based on recent behavior):
- Default categories:
  - Faulty: failed in the last 3 requests
  - Compliant: met the deadlines in the last 5 requests
  - Normal: any other case
- Faulty → can return to Normal after 3 consecutive on-time returns

Classification must be extensible for new criteria.

---

## Requests (Loans)
Request rules are checked in order (system rules):

1. A user cannot request the same work twice at the same time.
2. A suspended user cannot request works.
3. A user cannot request a work when there are no available copies.
4. Maximum simultaneous loans:
   - Normal: 3
   - Compliant: 5
   - Faulty: 1
5. Reference works cannot be loaned.
6. Works priced over €25 cannot be loaned (except by Compliant users).

If rule 3 is violated (no copies available), the user can ask to be notified when a copy becomes available.

Loan periods (in days) depend on the number of copies and user classification:

| Number of copies    | Normal | Compliant | Faulty |
|---------------------|--------|-----------|--------|
| 1 copy              | 3      | 8         | 2      |
| ≤ 5 copies          | 8      | 15        | 2      |
| > 5 copies          | 15     | 30        | 2      |

Fines: €5 per day of delay (fractions count as a full day).

Loan rules and deadlines must be easily extensible.

---

## Search
The system must allow searching by term across the relevant fields of each work. It should be easy to add new search methods with minimal impact.

---

## Inventory Changes
- The number of copies of a work can be adjusted by addition or subtraction.
- If the number of copies reaches 0 → the work is removed from the system.
- Removing works affects the list of creators (creators with no works should be removed).

---

## Time Management
- System date is measured in days, starting at 1.
- Advancing the date updates user statuses and due dates.
- The system date is part of the persisted state.

---

## Notifications
The system sends notifications when:
- A work is loaned
- An unavailable work becomes available again

Notes:
- Each notification is shown only once to the user.
- A user's interest in availability is removed when they are able to borrow the work.
- Interest in loan notifications persists while valid.

---

## Design Requirements
The architecture must follow the Open-Closed Principle.

The application must allow, with minimal impact:
- New work types
- New notifiable entities
- New loan rules
- New loan deadlines
- New user classifications
- Removing any entity

---

## Application Functionality
The system must:
- Maintain data for users, works, and requests
- Preserve state through serialization
- Allow removal of any entity
- Load initial state from a text file (if specified by a Java property import)

---

## User Interaction
A UI is pre-implemented in the supplied packages (bci-app).
- Commands receive data via forms and write via display.
- Domain exceptions must not use UI messages.
- The UI uses exceptions such as NoSuchUserException, NoSuchWorkException, etc.
- Operations that fail must not change application state (unless explicitly stated otherwise).

---

## Main Menu (already implemented in bci.app.main)
Includes:
- Open state file
- Save state
- Show date
- Advance date
- Users menu
- Works menu
- Requests menu

Save/load behavior:
- Open: replaces the current state
- Save: writes to the associated file
- Ask the user before discarding unsaved changes

---

## Users Management Menu (bci.app.users)
Includes:
- Register user
- Show user
- Show all users
- Show notifications
- Pay fine

Interaction follows classes in package bci.app.users.

---

## Works Management Menu
Includes:
- Show work
- Show all works
- Show works by creator
- Adjust inventory
- Search

Presentation formats vary depending on type (book or DVD).

---

## Requests Management Menu
Includes:
- Request work (borrow)
- Return work (deliver)

Rules and exceptions follow domain definitions.

---

## Initial Data File Formats
Work formats:
```
DVD:title:director:price:category:IGACNumber:copies
BOOK:title:author1,author2,...:price:category:ISBN:copies
```

User format:
```
USER:name:email
```

---
