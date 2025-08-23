# 🟢 Gatling Login Performance Test

This repository contains an example of a simple load test:  
1. Opening the main page  
2. Authorization using login and password  
3. Getting the profile page  

The scenario is parameterized — user data is taken from a CSV file.

---

## 📂 Project structure
```.

├── users.csv          
├── src/test/scala/LoginSimulation.scala
└── README.md
```

---

## ⚙️ How to run

1. Place `users.csv` in `src/test/resources`.  
2. Run the simulation:
```bash
   mvn gatling:test -Dgatling.simulationClass=LoginSimulation
   ```
   or via sbt:
```bash
   sbt “gatling:testOnly LoginSimulation”
   ```

---

## 🧩 CSV (users.csv)
```csv
username,password
testuser1,pass1
testuser2,pass2
testuser3,pass3
```

---

## 🎯 Purpose
- Demonstration of parameterization in Gatling.
- A basic example to get started, which can be expanded for real-world scenarios.

