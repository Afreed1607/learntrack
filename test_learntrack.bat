@echo off
REM Quick Test Script for LearnTrack (Windows)
REM Run this from the project root: test_learntrack.bat

echo =========================================
echo   LearnTrack - Automated Test
echo =========================================
echo.

REM Create input file for testing
(
echo 1
echo 1
echo John
echo Doe
echo john.doe@example.com
echo Java-2024
echo 8
echo 2
echo 1
echo Core Java
echo Learn Java fundamentals
echo 8
echo 8
echo 3
echo 1
echo 1001
echo 2001
echo 8
echo 4
echo 5
) > test_input.txt

REM Run the application with test input
java -cp bin com.airtribe.learntrack.Main < test_input.txt

REM Clean up
del test_input.txt

echo.
echo =========================================
echo   Test Complete
echo =========================================

pause

