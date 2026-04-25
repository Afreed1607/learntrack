#!/bin/bash
# Quick Test Script for LearnTrack
# This script demonstrates the application functionality

# Note: Run this from the project root: bash test_learntrack.sh
# Or use PowerShell equivalent

echo "========================================="
echo "  LearnTrack - Automated Test"
echo "========================================="
echo ""

# Create input file for testing
cat > test_input.txt << 'EOF'
1
1
John
Doe
john.doe@example.com
Java-2024
8
2
8
3
1
1001
2001
8
2
8
4
8
5
EOF

# Run the application with test input
java -cp bin com.airtribe.learntrack.Main < test_input.txt

# Clean up
rm test_input.txt

echo ""
echo "========================================="
echo "  Test Complete"
echo "========================================="

