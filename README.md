# Next Larger Word Problem

[Lexicographical order](https://en.wikipedia.org/wiki/Lexicographical_order) is often known as alphabetical order when dealing with strings. A string is greater than another string if it comes later in a lexicographically sorted list.

Given a word, create a new word by **swapping some or all** of its characters. This new word must meet three criteria:
- It must be _lexicographically_ greater than the original word
- It must be the _lexicographically_ smallest word that meets the first condition
- The word length should remain the same

For example, given the _word = **abcd**_ , the next larger word is **abdc**.

               
#### Input Constraints

The  _**word**_ String will contain only letters in the range ascii[a..z].

1 <= _**word.length()**_ <=100

**Note:** Assume these constraints mandatory for any function call, thus removing the need for checking

#### Samples

| Input| Output           | Explanation  |
| ------------- |-------------|-----|
| ab|ba|_ba_ is the only string which can be made by rearranging ab. It is greater.|
| bb|«no answer»|It is not possible to rearrange _bb_ and get a greater string.|
| hefg|hegf|_hegf_ is the next string greater than _hefg_.|
| dhck|dhkc|_dhkc_ is the next string greater than _dhck_.|
| dkhc|hcdk|_hcdk_ is the next string greater than _dkhc_.|


#### Function Description

Implement the _**Optional<String> nextLargerWord(String word)**_ method in the _LexicographicalOrder_ interface. 
It must return an _Optional_ with the next larger word (as described above) or _Optional.empty()_ if there is no answer.
