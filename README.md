# Smart Pantry Manager

## Project Overview

Smart Pantry Manager is an Android application developed in Java for the Mobile App Development 700 module at Richfield Graduate Institute of Technology.

The application helps users manage ingredients available in their pantry and discover recipes they can prepare using those ingredients. It uses an SQLite database to store ingredient information and a collection of 20 predefined recipes.

The main objective is to reduce unnecessary food waste, improve ingredient organisation and simplify everyday meal planning.

## Features

- Add pantry ingredients with names, quantities and measurement units.
- View all saved ingredients.
- Edit existing ingredients.
- Delete ingredients that are no longer available.
- Record optional ingredient expiry dates.
- Store pantry information persistently using SQLite.
- Recommend recipes based on available ingredients.
- Check that ingredients meet the quantities required by each recipe.
- Display recipe ingredients and preparation instructions.
- Validate required ingredient information and quantities.
- Provide a Settings screen with an expiry reminder preference.

**Note:** The expiry reminder preference can be saved, but automatic reminder notifications have not been implemented.

## Technologies Used

| Technology | Purpose |
|---|---|
| Java | Android application development |
| Android Studio | Development environment |
| Android SDK | Android application framework |
| SQLite | Local database |
| Git | Version control |
| GitHub | Source code repository |

## Database Implementation

The application uses **SQLite** as its database solution.

SQLite was selected because it is lightweight, available on Android, works without an internet connection and does not require a separate database server.

The database contains two main tables:

**Pantry table:** Stores ingredient names, quantities, measurement units and optional expiry dates.

**Recipes table:** Stores 20 predefined recipes, including recipe names, required ingredients, quantities and preparation instructions.

The application performs Create, Read, Update and Delete (CRUD) operations on pantry records.

## Recipe Recommendation Logic

Smart Pantry Manager uses strict ingredient matching.

A recipe is suggested only when all its required ingredients are available in sufficient quantities and compatible measurement units.

For example, Butter Toast requires:

- Bread: 2 slices
- Butter: 10 g

If the user has only 5 g of butter, Butter Toast will not appear in Suggested Recipes.

## Application Screens

1. My Pantry
2. Add Ingredient
3. Edit Ingredient
4. Suggested Recipes
5. Recipe Details
6. Settings

## How to Run the Project

### Requirements

- Android Studio
- JDK 17
- Android SDK
- Android emulator or compatible Android device

### Installation

1. Clone or download this repository.
2. Open Android Studio.
3. Select **Open**.
4. Choose the Smart Pantry Manager project folder.
5. Allow Gradle to synchronise the project.
6. Select an Android emulator or connected device.
7. Click **Run**.
8. Wait for the application to install and launch.

The application creates and accesses its local SQLite database automatically. No external database installation is required.

## How to Use the Application

1. Launch Smart Pantry Manager.
2. Open My Pantry.
3. Select Add Ingredient.
4. Enter the ingredient name, quantity and measurement unit.
5. Save the ingredient.
6. Repeat for additional ingredients.
7. Open Suggested Recipes to view matching recipes.
8. Select a recipe to view its ingredients and preparation method.
9. Tap an existing pantry ingredient to edit or delete it.
10. Open Settings to manage the available preference.

## Testing

The application has been tested using an Android emulator.

The following functionality has been demonstrated:

- Adding, viewing, editing and deleting pantry ingredients.
- Saving pantry information between application sessions.
- Displaying multiple matching recipes.
- Excluding recipes when ingredient quantities are insufficient.
- Opening recipe details.
- Detecting an empty required ingredient name.
- Confirming that the SQLite database contains 20 recipes.

## Known Limitations

- Automatic expiry reminder notifications are not implemented.
- Recipe suggestions are based on the 20 predefined recipes.
- Ingredient and recipe information is stored locally on the device.


## Project Status

The application implements the main pantry management and recipe recommendation features. Further testing and improvements may be carried out as part of development.
