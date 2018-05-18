function palindrome(str) {

    console.log(isPalindrome(str));

    function isPalindrome(input) {

        let lastIndex = input.length - 1;

        for (let i = 0; i <= lastIndex / 2; i++) {
            if (input[i] !== input[lastIndex - i]) {
                return false;
            }
        }
        return true;
    }
}

palindrome('haha');
palindrome('racecar');
palindrome('unitinu');