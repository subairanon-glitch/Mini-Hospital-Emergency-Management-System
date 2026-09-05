#!/bin/bash
# Progressive commit helper for the Mini Hospital Emergency Management System.
# Run this from the project root whenever you want to save progress.
# The only time it will ask you anything is the first time, for your GitHub URL.

set -e

# 1. Initialize the repo if it isn't one yet
if [ ! -d ".git" ]; then
    echo "No git repository found. Running git init..."
    git init
fi

# 2. Always make sure we're on 'main' (safe to re-run)
git branch -M main

# 3. Connect to GitHub only if 'origin' isn't already set
if ! git remote get-url origin >/dev/null 2>&1; then
    read -p "Enter your GitHub repository URL: " REPO_URL
    git remote add origin "$REPO_URL"
fi

# Helper: stage + commit a group of files only if something actually changed
commit_group() {
    local message="$1"
    shift
    local files=("$@")

    local existing=()
    for f in "${files[@]}"; do
        if [ -e "$f" ]; then
            existing+=("$f")
        fi
    done

    if [ ${#existing[@]} -eq 0 ]; then
        return
    fi

    git add "${existing[@]}"

    if git diff --cached --quiet; then
        # Nothing actually changed for this group
        git reset "${existing[@]}" >/dev/null 2>&1 || true
        return
    fi

    git commit -m "$message"
}

# 4. Commit progressively, grouped by component
commit_group "Implemented patient BST" src/Patient.java src/PatientBST.java
commit_group "Implemented emergency queue" src/EmergencyQueue.java
commit_group "Implemented treatment stack" src/TreatmentRecord.java src/TreatmentStack.java
commit_group "Implemented patient visit history" src/Visit.java src/VisitLinkedList.java
commit_group "Added main program and demo driver" src/Main.java src/Demo.java
commit_group "Updated README" README.md

# 5. Push once, at the end
git push -u origin main

echo "Done. All changes committed and pushed."
