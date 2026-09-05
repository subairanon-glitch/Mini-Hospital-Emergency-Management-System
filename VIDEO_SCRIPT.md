# Video Narration Script (target: 5-10 minutes)

Read this close to verbatim while recording. Rough time budget adds up to
about 8 minutes — trim pauses if you need to land under 10.

---

## 1. Introduction (face visible) — ~30 seconds

> "Hi, I'm [your name], and this is my CIT300 mid assignment — a Mini
> Hospital Emergency Management System built in Java. In this video I'll
> walk through my GitHub commit history, explain how each data structure is
> used, and then run the system live."

---

## 2. Brief system explanation — ~45 seconds

> "The system simulates a hospital's emergency department. It handles four
> things: storing patient records, managing the queue of patients waiting
> for treatment, keeping a history of completed treatments, and tracking
> each patient's past visits. Each of those four things is backed by a
> specific data structure that I implemented myself, not Java's built-in
> collections."

---

## 3. GitHub repository & commit history walkthrough — ~1 minute

> "Here's my GitHub repository. You can see I committed progressively as I
> built each part — I've got separate commits for the patient BST, the
> emergency queue, the treatment stack, the visit history linked list, the
> main program, and the README. I didn't upload everything as one final
> commit — each commit reflects a stage of actually building the system."

*(Screen: scroll through the commit history list, pointing at 2-3 specific commit messages.)*

---

## 4. Explaining each data structure — ~2 minutes

> "First, patient records. I used a Binary Search Tree keyed on Patient ID.
> That gives me fast search, and an in-order traversal automatically lists
> patients in ascending ID order, which is exactly what's needed here."

> "Second, the emergency queue. Patients waiting for treatment are stored in
> a FIFO queue — first patient in is the first one dequeued for treatment. I
> built this with a linked list under the hood, tracking a front and a rear
> pointer."

> "Third, treatment history. Once a patient's treatment is completed, I push
> a record onto a stack. That's LIFO — last in, first out — so the most
> recently completed treatment is always the first one you see or can pop
> off."

> "Fourth, patient visit history. Each patient object holds its own singly
> linked list of past visits — I can add a new visit, remove one by visit
> ID, search for one, and display the full list for that patient."

---

## 5. Demonstration of the system running — ~2.5 minutes

> "Now let me run it live."

*(Run `java Demo` — let the printed output speak for each section; narrate only the headline of what's about to happen, then pause while it prints, per part below.)*

- **BST section:** "Watch — I'm inserting four patients, searching for one
  by ID, showing the in-order traversal, then deleting one and traversing
  again to confirm it's gone." *(pause while output prints)*
- **Queue section:** "Here I show an empty queue, enqueue three patients,
  display the queue, then dequeue the next one for treatment." *(pause)*
- **Stack section:** "Now the treatment stack — empty at first, I push two
  completed treatments, display the stack most-recent-first, then pop the
  top one off." *(pause)*
- **Linked list section:** "And finally, one patient's visit history —
  empty, then I add two visits, search for one by ID, remove it, and show
  the list again." *(pause)*

---

## 6. Design decisions — ~45 seconds

> "A couple of decisions worth calling out: I gave each Patient object its
> own visit-history linked list, so history is naturally scoped per patient
> instead of one big shared list. I also wired the queue and the stack
> together — when you dequeue a patient for treatment, the system prompts
> for the treatment details right there and pushes the resulting record onto
> the stack, so the workflow mirrors what actually happens in an emergency
> room: waiting, then being treated, then that treatment being recorded."

---

## 7. Reflection — ~30 seconds

> "Building this helped me actually see why you'd pick one data structure
> over another — the BST for fast, sorted lookups, the queue for fairness in
> treatment order, the stack for reviewing the most recent work first, and
> the linked list for a simple, per-patient growing history. That's my
> submission — thanks for watching."

---

**Reminder:** don't backdate or artificially space out your git commits to
make the history look more spread out — the assignment reviews commit
timestamps as evidence of real, incremental work, and doing that would
misrepresent it.
