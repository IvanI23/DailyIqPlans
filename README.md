# 🗒️ DailyIqPlans

A Java web-based notes planner that supports multiple note types, time-based categorization, search, and editing, all wrapped in a modular, MVC-driven architecture.

## 📌 Features

- **Multi-type Notes**: Create notes with text and optional image URLs.
- **Smart Categorization**:
  - Time-based: *Yesterday*, *Today*, *Tomorrow*
  - Period-based: *Morning*, *Afternoon*, *Evening*
  - Completion-based: *Completed*, *Incomplete*, *All*
- **Interactive Indexing**: Notes auto-arranged by time categories. Clickable icons reveal more details.
- **Note Management**: Edit, delete, rename, or mark notes complete with a single click.
- **Search Functionality**: Quickly find notes via keyword search.
- **Persistent Storage**: Notes are automatically saved in a `plans.csv` file.
- **Pop-Up Preview**: Modal window shows quick note previews with deeper detail on full view.

## 🧱 Design Overview

- **Architecture**: Follows the Model-View-Controller (MVC) pattern using Java Servlets for controller logic.
- **Development Approach**: Built using Object-Oriented Design (OOD) and Test-Driven Development (TDD).
- **Design Principles**:
  - Follows SOLID principles (Single Responsibility, Liskov Substitution, etc.)
  - Abstract base model with common methods; each model class is responsible for a single action.
  - Emphasizes composition over inheritance and minimizes tight coupling.

## 🛠️ Model Components

- `formatModal` (Abstract): Base class for shared functionality.
- `AddModel`: Handles new note entries.
- `DeleteModel`: Deletes selected notes.
- `EditModel`: Edits existing notes while preserving structure.
- `PlanModel`: Displays all plans by date.
- `SortPlanModel`: Filters by completion status.
- `SearchModel`: Searches for keywords in CSV data.
