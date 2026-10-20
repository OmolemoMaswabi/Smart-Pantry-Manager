# Smart Pantry Manager
Java Android app with local SQLite storage, pantry CRUD, 20 seeded recipes and strict quantity-aware suggestions.

## Run
1. Install Android Studio with Android SDK 35 and JDK 17.
2. Open the `SmartPantryManager` folder as a project, allow Gradle sync (internet needed for initial Gradle/Android plugin downloads).
3. Select an Android emulator/API 23+ or connected Android device and click Run.
4. Add ingredients using **Add ingredient**, tap a row to edit/delete, and open **Suggested recipes** to check matches.

## Database choice
SQLiteOpenHelper was chosen because this is an offline, single-user pantry app. SQLite persists pantry rows and seeds 20 recipes when the database is first created.

## Test strict matching
Add `bread` 2 `slice` and `butter` 10 `g`: Butter Toast appears. Edit butter to 5 g: it disappears. Change to 0.01 kg: it appears again. Add `tomatoes` 2 `piece` and `salt` 1 `g`: Tomato Salad appears.

## Notes
Supported units: g/kg, ml/l, piece, slice. Units that cannot be converted are intentionally not equated. Expiry date is informational, not part of the matching rule. Settings toggle is a saved preference, not a scheduled notification.

## GitHub and evidence
Create a PUBLIC repository and commit genuine incremental work throughout development; do not manufacture history. Record your own 5–7 minute narrated demo, actual emulator screenshots, and GitHub history before final submission. The supplied source is not a substitute for those artifacts.
