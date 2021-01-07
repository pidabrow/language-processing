# language-processing

**Algorithm:**
The application constructs a tree-based model for consecutive words. Based on this structure, extracting all possible skip-grams is done using Depth First Serach algorithm.


**TODO:**

(1) limiting max skip-gram length

* naive solution - having the tree-based model in place along with all possible skip-grams it's easy to filter out the skip-grams that don't match max length

* optimal solution - while constructing the tree-based model, there should be information in each node what's the depth of given element starting with root depth = 0. 

(2) limiting max gap size

* naive solution - same approach as for max skip-gram length

* optimal solution - while creating nodes in the tree-based model, there should be an additional constraint that will check what would be the gap size if the node were created. 

Running locally
====================
Work in progress that needs some final tweaking. Running locally, on localhost:8080 and visiting localhost:8080/skip-gram/demo produces output like below. 

**How to read this?**  
Assuming original phrase `The quick brown fox jumps over the lazy dog` and sample result like following:  
`The quick brown fox jumps over the dog (gaps: 0,0,0,0,0,0,1)`, it means that there's a gap between last two word. In this case there's one word missing (`lazy`), which is between `lazy` and `dog` in the original sentence.   
`The` -> (0) -> `quick` -> (0) -> `brown` -> (0) -> `fox` -> (0) -> `jumps` -> (0) -> `over` -> (0) ->`the` -> (1) -> `dog`.

**Annother example:**  
`The quick fox dog (gaps: 0,1,4)` -> `The` -> (0) -> `quick` -> (1) -> `fox` -> (4) -> `dog`  
There are two gaps in this skip-gram, one of size 1 (between `quick` and `fox`, there's `brown` missing), and the other of size 4 (between `fox` and `dog` there are missing `jumps`, `over`, `the` and `lazy`).

**Note:**
For the best user experience in browser, I recommend using JSON Formatter like this one for Google Chrome:
https://github.com/callumlocke/json-formatter (there's a link to Play Store on the right)
```
The quick brown fox jumps over the lazy dog
The quick brown fox jumps over the lazy
The quick brown fox jumps over the dog (gaps: 0,0,0,0,0,0,1)
The quick brown fox jumps over the
The quick brown fox jumps over lazy dog (gaps: 0,0,0,0,0,1,0)
The quick brown fox jumps over lazy (gaps: 0,0,0,0,0,1)
The quick brown fox jumps over dog (gaps: 0,0,0,0,0,2)
The quick brown fox jumps over
The quick brown fox jumps the lazy dog (gaps: 0,0,0,0,1,0,0)
The quick brown fox jumps the lazy (gaps: 0,0,0,0,1,0)
The quick brown fox jumps the dog (gaps: 0,0,0,0,1,1)
The quick brown fox jumps the (gaps: 0,0,0,0,1)
The quick brown fox jumps lazy dog (gaps: 0,0,0,0,2,0)
The quick brown fox jumps lazy (gaps: 0,0,0,0,2)
The quick brown fox jumps dog (gaps: 0,0,0,0,3)
The quick brown fox jumps
The quick brown fox over the lazy dog (gaps: 0,0,0,1,0,0,0)
The quick brown fox over the lazy (gaps: 0,0,0,1,0,0)
The quick brown fox over the dog (gaps: 0,0,0,1,0,1)
The quick brown fox over the (gaps: 0,0,0,1,0)
The quick brown fox over lazy dog (gaps: 0,0,0,1,1,0)
The quick brown fox over lazy (gaps: 0,0,0,1,1)
The quick brown fox over dog (gaps: 0,0,0,1,2)
The quick brown fox over (gaps: 0,0,0,1)
The quick brown fox the lazy dog (gaps: 0,0,0,2,0,0)
The quick brown fox the lazy (gaps: 0,0,0,2,0)
The quick brown fox the dog (gaps: 0,0,0,2,1)
The quick brown fox the (gaps: 0,0,0,2)
The quick brown fox lazy dog (gaps: 0,0,0,3,0)
The quick brown fox lazy (gaps: 0,0,0,3)
The quick brown fox dog (gaps: 0,0,0,4)
The quick brown fox
The quick brown jumps over the lazy dog (gaps: 0,0,1,0,0,0,0)
The quick brown jumps over the lazy (gaps: 0,0,1,0,0,0)
The quick brown jumps over the dog (gaps: 0,0,1,0,0,1)
The quick brown jumps over the (gaps: 0,0,1,0,0)
The quick brown jumps over lazy dog (gaps: 0,0,1,0,1,0)
The quick brown jumps over lazy (gaps: 0,0,1,0,1)
The quick brown jumps over dog (gaps: 0,0,1,0,2)
The quick brown jumps over (gaps: 0,0,1,0)
The quick brown jumps the lazy dog (gaps: 0,0,1,1,0,0)
The quick brown jumps the lazy (gaps: 0,0,1,1,0)
The quick brown jumps the dog (gaps: 0,0,1,1,1)
The quick brown jumps the (gaps: 0,0,1,1)
The quick brown jumps lazy dog (gaps: 0,0,1,2,0)
The quick brown jumps lazy (gaps: 0,0,1,2)
The quick brown jumps dog (gaps: 0,0,1,3)
The quick brown jumps (gaps: 0,0,1)
The quick brown over the lazy dog (gaps: 0,0,2,0,0,0)
The quick brown over the lazy (gaps: 0,0,2,0,0)
The quick brown over the dog (gaps: 0,0,2,0,1)
The quick brown over the (gaps: 0,0,2,0)
The quick brown over lazy dog (gaps: 0,0,2,1,0)
The quick brown over lazy (gaps: 0,0,2,1)
The quick brown over dog (gaps: 0,0,2,2)
The quick brown over (gaps: 0,0,2)
The quick brown the lazy dog (gaps: 0,0,3,0,0)
The quick brown the lazy (gaps: 0,0,3,0)
The quick brown the dog (gaps: 0,0,3,1)
The quick brown the (gaps: 0,0,3)
The quick brown lazy dog (gaps: 0,0,4,0)
The quick brown lazy (gaps: 0,0,4)
The quick brown dog (gaps: 0,0,5)
The quick brown
The quick fox jumps over the lazy dog (gaps: 0,1,0,0,0,0,0)
The quick fox jumps over the lazy (gaps: 0,1,0,0,0,0)
The quick fox jumps over the dog (gaps: 0,1,0,0,0,1)
The quick fox jumps over the (gaps: 0,1,0,0,0)
The quick fox jumps over lazy dog (gaps: 0,1,0,0,1,0)
The quick fox jumps over lazy (gaps: 0,1,0,0,1)
The quick fox jumps over dog (gaps: 0,1,0,0,2)
The quick fox jumps over (gaps: 0,1,0,0)
The quick fox jumps the lazy dog (gaps: 0,1,0,1,0,0)
The quick fox jumps the lazy (gaps: 0,1,0,1,0)
The quick fox jumps the dog (gaps: 0,1,0,1,1)
The quick fox jumps the (gaps: 0,1,0,1)
The quick fox jumps lazy dog (gaps: 0,1,0,2,0)
The quick fox jumps lazy (gaps: 0,1,0,2)
The quick fox jumps dog (gaps: 0,1,0,3)
The quick fox jumps (gaps: 0,1,0)
The quick fox over the lazy dog (gaps: 0,1,1,0,0,0)
The quick fox over the lazy (gaps: 0,1,1,0,0)
The quick fox over the dog (gaps: 0,1,1,0,1)
The quick fox over the (gaps: 0,1,1,0)
The quick fox over lazy dog (gaps: 0,1,1,1,0)
The quick fox over lazy (gaps: 0,1,1,1)
The quick fox over dog (gaps: 0,1,1,2)
The quick fox over (gaps: 0,1,1)
The quick fox the lazy dog (gaps: 0,1,2,0,0)
The quick fox the lazy (gaps: 0,1,2,0)
The quick fox the dog (gaps: 0,1,2,1)
The quick fox the (gaps: 0,1,2)
The quick fox lazy dog (gaps: 0,1,3,0)
The quick fox lazy (gaps: 0,1,3)
The quick fox dog (gaps: 0,1,4)
The quick fox (gaps: 0,1)
The quick jumps over the lazy dog (gaps: 0,2,0,0,0,0)
The quick jumps over the lazy (gaps: 0,2,0,0,0)
The quick jumps over the dog (gaps: 0,2,0,0,1)
The quick jumps over the (gaps: 0,2,0,0)
The quick jumps over lazy dog (gaps: 0,2,0,1,0)
The quick jumps over lazy (gaps: 0,2,0,1)
The quick jumps over dog (gaps: 0,2,0,2)
The quick jumps over (gaps: 0,2,0)
The quick jumps the lazy dog (gaps: 0,2,1,0,0)
The quick jumps the lazy (gaps: 0,2,1,0)
The quick jumps the dog (gaps: 0,2,1,1)
The quick jumps the (gaps: 0,2,1)
The quick jumps lazy dog (gaps: 0,2,2,0)
The quick jumps lazy (gaps: 0,2,2)
The quick jumps dog (gaps: 0,2,3)
The quick jumps (gaps: 0,2)
The quick over the lazy dog (gaps: 0,3,0,0,0)
The quick over the lazy (gaps: 0,3,0,0)
The quick over the dog (gaps: 0,3,0,1)
The quick over the (gaps: 0,3,0)
The quick over lazy dog (gaps: 0,3,1,0)
The quick over lazy (gaps: 0,3,1)
The quick over dog (gaps: 0,3,2)
The quick over (gaps: 0,3)
The quick the lazy dog (gaps: 0,4,0,0)
The quick the lazy (gaps: 0,4,0)
The quick the dog (gaps: 0,4,1)
The quick the (gaps: 0,4)
The quick lazy dog (gaps: 0,5,0)
The quick lazy (gaps: 0,5)
The quick dog (gaps: 0,6)
The quick
The brown fox jumps over the lazy dog (gaps: 1,0,0,0,0,0,0)
The brown fox jumps over the lazy (gaps: 1,0,0,0,0,0)
The brown fox jumps over the dog (gaps: 1,0,0,0,0,1)
The brown fox jumps over the (gaps: 1,0,0,0,0)
The brown fox jumps over lazy dog (gaps: 1,0,0,0,1,0)
The brown fox jumps over lazy (gaps: 1,0,0,0,1)
The brown fox jumps over dog (gaps: 1,0,0,0,2)
The brown fox jumps over (gaps: 1,0,0,0)
The brown fox jumps the lazy dog (gaps: 1,0,0,1,0,0)
The brown fox jumps the lazy (gaps: 1,0,0,1,0)
The brown fox jumps the dog (gaps: 1,0,0,1,1)
The brown fox jumps the (gaps: 1,0,0,1)
The brown fox jumps lazy dog (gaps: 1,0,0,2,0)
The brown fox jumps lazy (gaps: 1,0,0,2)
The brown fox jumps dog (gaps: 1,0,0,3)
The brown fox jumps (gaps: 1,0,0)
The brown fox over the lazy dog (gaps: 1,0,1,0,0,0)
The brown fox over the lazy (gaps: 1,0,1,0,0)
The brown fox over the dog (gaps: 1,0,1,0,1)
The brown fox over the (gaps: 1,0,1,0)
The brown fox over lazy dog (gaps: 1,0,1,1,0)
The brown fox over lazy (gaps: 1,0,1,1)
The brown fox over dog (gaps: 1,0,1,2)
The brown fox over (gaps: 1,0,1)
The brown fox the lazy dog (gaps: 1,0,2,0,0)
The brown fox the lazy (gaps: 1,0,2,0)
The brown fox the dog (gaps: 1,0,2,1)
The brown fox the (gaps: 1,0,2)
The brown fox lazy dog (gaps: 1,0,3,0)
The brown fox lazy (gaps: 1,0,3)
The brown fox dog (gaps: 1,0,4)
The brown fox (gaps: 1,0)
The brown jumps over the lazy dog (gaps: 1,1,0,0,0,0)
The brown jumps over the lazy (gaps: 1,1,0,0,0)
The brown jumps over the dog (gaps: 1,1,0,0,1)
The brown jumps over the (gaps: 1,1,0,0)
The brown jumps over lazy dog (gaps: 1,1,0,1,0)
The brown jumps over lazy (gaps: 1,1,0,1)
The brown jumps over dog (gaps: 1,1,0,2)
The brown jumps over (gaps: 1,1,0)
The brown jumps the lazy dog (gaps: 1,1,1,0,0)
The brown jumps the lazy (gaps: 1,1,1,0)
The brown jumps the dog (gaps: 1,1,1,1)
The brown jumps the (gaps: 1,1,1)
The brown jumps lazy dog (gaps: 1,1,2,0)
The brown jumps lazy (gaps: 1,1,2)
The brown jumps dog (gaps: 1,1,3)
The brown jumps (gaps: 1,1)
The brown over the lazy dog (gaps: 1,2,0,0,0)
The brown over the lazy (gaps: 1,2,0,0)
The brown over the dog (gaps: 1,2,0,1)
The brown over the (gaps: 1,2,0)
The brown over lazy dog (gaps: 1,2,1,0)
The brown over lazy (gaps: 1,2,1)
The brown over dog (gaps: 1,2,2)
The brown over (gaps: 1,2)
The brown the lazy dog (gaps: 1,3,0,0)
The brown the lazy (gaps: 1,3,0)
The brown the dog (gaps: 1,3,1)
The brown the (gaps: 1,3)
The brown lazy dog (gaps: 1,4,0)
The brown lazy (gaps: 1,4)
The brown dog (gaps: 1,5)
The brown (gaps: 1)
The fox jumps over the lazy dog (gaps: 2,0,0,0,0,0)
The fox jumps over the lazy (gaps: 2,0,0,0,0)
The fox jumps over the dog (gaps: 2,0,0,0,1)
The fox jumps over the (gaps: 2,0,0,0)
The fox jumps over lazy dog (gaps: 2,0,0,1,0)
The fox jumps over lazy (gaps: 2,0,0,1)
The fox jumps over dog (gaps: 2,0,0,2)
The fox jumps over (gaps: 2,0,0)
The fox jumps the lazy dog (gaps: 2,0,1,0,0)
The fox jumps the lazy (gaps: 2,0,1,0)
The fox jumps the dog (gaps: 2,0,1,1)
The fox jumps the (gaps: 2,0,1)
The fox jumps lazy dog (gaps: 2,0,2,0)
The fox jumps lazy (gaps: 2,0,2)
The fox jumps dog (gaps: 2,0,3)
The fox jumps (gaps: 2,0)
The fox over the lazy dog (gaps: 2,1,0,0,0)
The fox over the lazy (gaps: 2,1,0,0)
The fox over the dog (gaps: 2,1,0,1)
The fox over the (gaps: 2,1,0)
The fox over lazy dog (gaps: 2,1,1,0)
The fox over lazy (gaps: 2,1,1)
The fox over dog (gaps: 2,1,2)
The fox over (gaps: 2,1)
The fox the lazy dog (gaps: 2,2,0,0)
The fox the lazy (gaps: 2,2,0)
The fox the dog (gaps: 2,2,1)
The fox the (gaps: 2,2)
The fox lazy dog (gaps: 2,3,0)
The fox lazy (gaps: 2,3)
The fox dog (gaps: 2,4)
The fox (gaps: 2)
The jumps over the lazy dog (gaps: 3,0,0,0,0)
The jumps over the lazy (gaps: 3,0,0,0)
The jumps over the dog (gaps: 3,0,0,1)
The jumps over the (gaps: 3,0,0)
The jumps over lazy dog (gaps: 3,0,1,0)
The jumps over lazy (gaps: 3,0,1)
The jumps over dog (gaps: 3,0,2)
The jumps over (gaps: 3,0)
The jumps the lazy dog (gaps: 3,1,0,0)
The jumps the lazy (gaps: 3,1,0)
The jumps the dog (gaps: 3,1,1)
The jumps the (gaps: 3,1)
The jumps lazy dog (gaps: 3,2,0)
The jumps lazy (gaps: 3,2)
The jumps dog (gaps: 3,3)
The jumps (gaps: 3)
The over the lazy dog (gaps: 4,0,0,0)
The over the lazy (gaps: 4,0,0)
The over the dog (gaps: 4,0,1)
The over the (gaps: 4,0)
The over lazy dog (gaps: 4,1,0)
The over lazy (gaps: 4,1)
The over dog (gaps: 4,2)
The over (gaps: 4)
The the lazy dog (gaps: 5,0,0)
The the lazy (gaps: 5,0)
The the dog (gaps: 5,1)
The the (gaps: 5)
The lazy dog (gaps: 6,0)
The lazy (gaps: 6)
The dog (gaps: 7)
The
quick brown fox jumps over the lazy dog
quick brown fox jumps over the lazy
quick brown fox jumps over the dog (gaps: 0,0,0,0,0,1)
quick brown fox jumps over the
quick brown fox jumps over lazy dog (gaps: 0,0,0,0,1,0)
quick brown fox jumps over lazy (gaps: 0,0,0,0,1)
quick brown fox jumps over dog (gaps: 0,0,0,0,2)
quick brown fox jumps over
quick brown fox jumps the lazy dog (gaps: 0,0,0,1,0,0)
quick brown fox jumps the lazy (gaps: 0,0,0,1,0)
quick brown fox jumps the dog (gaps: 0,0,0,1,1)
quick brown fox jumps the (gaps: 0,0,0,1)
quick brown fox jumps lazy dog (gaps: 0,0,0,2,0)
quick brown fox jumps lazy (gaps: 0,0,0,2)
quick brown fox jumps dog (gaps: 0,0,0,3)
quick brown fox jumps
quick brown fox over the lazy dog (gaps: 0,0,1,0,0,0)
quick brown fox over the lazy (gaps: 0,0,1,0,0)
quick brown fox over the dog (gaps: 0,0,1,0,1)
quick brown fox over the (gaps: 0,0,1,0)
quick brown fox over lazy dog (gaps: 0,0,1,1,0)
quick brown fox over lazy (gaps: 0,0,1,1)
quick brown fox over dog (gaps: 0,0,1,2)
quick brown fox over (gaps: 0,0,1)
quick brown fox the lazy dog (gaps: 0,0,2,0,0)
quick brown fox the lazy (gaps: 0,0,2,0)
quick brown fox the dog (gaps: 0,0,2,1)
quick brown fox the (gaps: 0,0,2)
quick brown fox lazy dog (gaps: 0,0,3,0)
quick brown fox lazy (gaps: 0,0,3)
quick brown fox dog (gaps: 0,0,4)
quick brown fox
quick brown jumps over the lazy dog (gaps: 0,1,0,0,0,0)
quick brown jumps over the lazy (gaps: 0,1,0,0,0)
quick brown jumps over the dog (gaps: 0,1,0,0,1)
quick brown jumps over the (gaps: 0,1,0,0)
quick brown jumps over lazy dog (gaps: 0,1,0,1,0)
quick brown jumps over lazy (gaps: 0,1,0,1)
quick brown jumps over dog (gaps: 0,1,0,2)
quick brown jumps over (gaps: 0,1,0)
quick brown jumps the lazy dog (gaps: 0,1,1,0,0)
quick brown jumps the lazy (gaps: 0,1,1,0)
quick brown jumps the dog (gaps: 0,1,1,1)
quick brown jumps the (gaps: 0,1,1)
quick brown jumps lazy dog (gaps: 0,1,2,0)
quick brown jumps lazy (gaps: 0,1,2)
quick brown jumps dog (gaps: 0,1,3)
quick brown jumps (gaps: 0,1)
quick brown over the lazy dog (gaps: 0,2,0,0,0)
quick brown over the lazy (gaps: 0,2,0,0)
quick brown over the dog (gaps: 0,2,0,1)
quick brown over the (gaps: 0,2,0)
quick brown over lazy dog (gaps: 0,2,1,0)
quick brown over lazy (gaps: 0,2,1)
quick brown over dog (gaps: 0,2,2)
quick brown over (gaps: 0,2)
quick brown the lazy dog (gaps: 0,3,0,0)
quick brown the lazy (gaps: 0,3,0)
quick brown the dog (gaps: 0,3,1)
quick brown the (gaps: 0,3)
quick brown lazy dog (gaps: 0,4,0)
quick brown lazy (gaps: 0,4)
quick brown dog (gaps: 0,5)
quick brown
quick fox jumps over the lazy dog (gaps: 1,0,0,0,0,0)
quick fox jumps over the lazy (gaps: 1,0,0,0,0)
quick fox jumps over the dog (gaps: 1,0,0,0,1)
quick fox jumps over the (gaps: 1,0,0,0)
quick fox jumps over lazy dog (gaps: 1,0,0,1,0)
quick fox jumps over lazy (gaps: 1,0,0,1)
quick fox jumps over dog (gaps: 1,0,0,2)
quick fox jumps over (gaps: 1,0,0)
quick fox jumps the lazy dog (gaps: 1,0,1,0,0)
quick fox jumps the lazy (gaps: 1,0,1,0)
quick fox jumps the dog (gaps: 1,0,1,1)
quick fox jumps the (gaps: 1,0,1)
quick fox jumps lazy dog (gaps: 1,0,2,0)
quick fox jumps lazy (gaps: 1,0,2)
quick fox jumps dog (gaps: 1,0,3)
quick fox jumps (gaps: 1,0)
quick fox over the lazy dog (gaps: 1,1,0,0,0)
quick fox over the lazy (gaps: 1,1,0,0)
quick fox over the dog (gaps: 1,1,0,1)
quick fox over the (gaps: 1,1,0)
quick fox over lazy dog (gaps: 1,1,1,0)
quick fox over lazy (gaps: 1,1,1)
quick fox over dog (gaps: 1,1,2)
quick fox over (gaps: 1,1)
quick fox the lazy dog (gaps: 1,2,0,0)
quick fox the lazy (gaps: 1,2,0)
quick fox the dog (gaps: 1,2,1)
quick fox the (gaps: 1,2)
quick fox lazy dog (gaps: 1,3,0)
quick fox lazy (gaps: 1,3)
quick fox dog (gaps: 1,4)
quick fox (gaps: 1)
quick jumps over the lazy dog (gaps: 2,0,0,0,0)
quick jumps over the lazy (gaps: 2,0,0,0)
quick jumps over the dog (gaps: 2,0,0,1)
quick jumps over the (gaps: 2,0,0)
quick jumps over lazy dog (gaps: 2,0,1,0)
quick jumps over lazy (gaps: 2,0,1)
quick jumps over dog (gaps: 2,0,2)
quick jumps over (gaps: 2,0)
quick jumps the lazy dog (gaps: 2,1,0,0)
quick jumps the lazy (gaps: 2,1,0)
quick jumps the dog (gaps: 2,1,1)
quick jumps the (gaps: 2,1)
quick jumps lazy dog (gaps: 2,2,0)
quick jumps lazy (gaps: 2,2)
quick jumps dog (gaps: 2,3)
quick jumps (gaps: 2)
quick over the lazy dog (gaps: 3,0,0,0)
quick over the lazy (gaps: 3,0,0)
quick over the dog (gaps: 3,0,1)
quick over the (gaps: 3,0)
quick over lazy dog (gaps: 3,1,0)
quick over lazy (gaps: 3,1)
quick over dog (gaps: 3,2)
quick over (gaps: 3)
quick the lazy dog (gaps: 4,0,0)
quick the lazy (gaps: 4,0)
quick the dog (gaps: 4,1)
quick the (gaps: 4)
quick lazy dog (gaps: 5,0)
quick lazy (gaps: 5)
quick dog (gaps: 6)
quick
brown fox jumps over the lazy dog
brown fox jumps over the lazy
brown fox jumps over the dog (gaps: 0,0,0,0,1)
brown fox jumps over the
brown fox jumps over lazy dog (gaps: 0,0,0,1,0)
brown fox jumps over lazy (gaps: 0,0,0,1)
brown fox jumps over dog (gaps: 0,0,0,2)
brown fox jumps over
brown fox jumps the lazy dog (gaps: 0,0,1,0,0)
brown fox jumps the lazy (gaps: 0,0,1,0)
brown fox jumps the dog (gaps: 0,0,1,1)
brown fox jumps the (gaps: 0,0,1)
brown fox jumps lazy dog (gaps: 0,0,2,0)
brown fox jumps lazy (gaps: 0,0,2)
brown fox jumps dog (gaps: 0,0,3)
brown fox jumps
brown fox over the lazy dog (gaps: 0,1,0,0,0)
brown fox over the lazy (gaps: 0,1,0,0)
brown fox over the dog (gaps: 0,1,0,1)
brown fox over the (gaps: 0,1,0)
brown fox over lazy dog (gaps: 0,1,1,0)
brown fox over lazy (gaps: 0,1,1)
brown fox over dog (gaps: 0,1,2)
brown fox over (gaps: 0,1)
brown fox the lazy dog (gaps: 0,2,0,0)
brown fox the lazy (gaps: 0,2,0)
brown fox the dog (gaps: 0,2,1)
brown fox the (gaps: 0,2)
brown fox lazy dog (gaps: 0,3,0)
brown fox lazy (gaps: 0,3)
brown fox dog (gaps: 0,4)
brown fox
brown jumps over the lazy dog (gaps: 1,0,0,0,0)
brown jumps over the lazy (gaps: 1,0,0,0)
brown jumps over the dog (gaps: 1,0,0,1)
brown jumps over the (gaps: 1,0,0)
brown jumps over lazy dog (gaps: 1,0,1,0)
brown jumps over lazy (gaps: 1,0,1)
brown jumps over dog (gaps: 1,0,2)
brown jumps over (gaps: 1,0)
brown jumps the lazy dog (gaps: 1,1,0,0)
brown jumps the lazy (gaps: 1,1,0)
brown jumps the dog (gaps: 1,1,1)
brown jumps the (gaps: 1,1)
brown jumps lazy dog (gaps: 1,2,0)
brown jumps lazy (gaps: 1,2)
brown jumps dog (gaps: 1,3)
brown jumps (gaps: 1)
brown over the lazy dog (gaps: 2,0,0,0)
brown over the lazy (gaps: 2,0,0)
brown over the dog (gaps: 2,0,1)
brown over the (gaps: 2,0)
brown over lazy dog (gaps: 2,1,0)
brown over lazy (gaps: 2,1)
brown over dog (gaps: 2,2)
brown over (gaps: 2)
brown the lazy dog (gaps: 3,0,0)
brown the lazy (gaps: 3,0)
brown the dog (gaps: 3,1)
brown the (gaps: 3)
brown lazy dog (gaps: 4,0)
brown lazy (gaps: 4)
brown dog (gaps: 5)
brown
fox jumps over the lazy dog
fox jumps over the lazy
fox jumps over the dog (gaps: 0,0,0,1)
fox jumps over the
fox jumps over lazy dog (gaps: 0,0,1,0)
fox jumps over lazy (gaps: 0,0,1)
fox jumps over dog (gaps: 0,0,2)
fox jumps over
fox jumps the lazy dog (gaps: 0,1,0,0)
fox jumps the lazy (gaps: 0,1,0)
fox jumps the dog (gaps: 0,1,1)
fox jumps the (gaps: 0,1)
fox jumps lazy dog (gaps: 0,2,0)
fox jumps lazy (gaps: 0,2)
fox jumps dog (gaps: 0,3)
fox jumps
fox over the lazy dog (gaps: 1,0,0,0)
fox over the lazy (gaps: 1,0,0)
fox over the dog (gaps: 1,0,1)
fox over the (gaps: 1,0)
fox over lazy dog (gaps: 1,1,0)
fox over lazy (gaps: 1,1)
fox over dog (gaps: 1,2)
fox over (gaps: 1)
fox the lazy dog (gaps: 2,0,0)
fox the lazy (gaps: 2,0)
fox the dog (gaps: 2,1)
fox the (gaps: 2)
fox lazy dog (gaps: 3,0)
fox lazy (gaps: 3)
fox dog (gaps: 4)
fox
jumps over the lazy dog
jumps over the lazy
jumps over the dog (gaps: 0,0,1)
jumps over the
jumps over lazy dog (gaps: 0,1,0)
jumps over lazy (gaps: 0,1)
jumps over dog (gaps: 0,2)
jumps over
jumps the lazy dog (gaps: 1,0,0)
jumps the lazy (gaps: 1,0)
jumps the dog (gaps: 1,1)
jumps the (gaps: 1)
jumps lazy dog (gaps: 2,0)
jumps lazy (gaps: 2)
jumps dog (gaps: 3)
jumps
over the lazy dog
over the lazy
over the dog (gaps: 0,1)
over the
over lazy dog (gaps: 1,0)
over lazy (gaps: 1)
over dog (gaps: 2)
over
the lazy dog
the lazy
the dog (gaps: 1)
the
lazy dog
lazy
dog
```
