# Manual test cases
1. Fresh install: 20 recipes seeded; no pantry items; no suggestions.
2. Add bread 2 slice + butter 10 g: Butter Toast shown.
3. Edit butter to 5 g: Butter Toast hidden.
4. Edit butter to 0.01 kg: Butter Toast shown (unit conversion).
5. Delete bread: Butter Toast hidden.
6. Add tomato 2 piece and salt 1 g: Tomato Salad shown.
7. Replace tomato with tomatoes: Tomato Salad remains shown (plural normalization).
8. Add quantity 0 or unsupported unit: validation prevents saving.
9. Restart app: saved pantry rows remain.
10. Tap suggested recipe: ingredients and preparation steps displayed.
11. Toggle settings and reopen settings: preference remains.
