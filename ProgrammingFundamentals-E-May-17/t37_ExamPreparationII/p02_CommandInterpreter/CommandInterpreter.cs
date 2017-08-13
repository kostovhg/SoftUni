using System;

namespace p02_CommandInterpreter
{
    class CommandInterpreter
    {
        static void Main(string[] args)
        {
            // split first input and remove all white spaces!!!
            string[] theArray = Console.ReadLine().Split(new char[] { ' ' },
                StringSplitOptions.RemoveEmptyEntries);
            // declare strig array to be readed every tyme while loop starts
            string[] input;
            // check if readed from console string array first member is "end"
            while (!"end".Equals((input = Console.ReadLine().Split())[0]))
            {
                // read the command and return int array [ commandIndex, count, start ]
                // with special method. Important -> custom metod should filter all invalid commands
                // see comments in the method!
                int[] command = RefineInput(input, theArray.Length);
                // check for invalid commands (refinedInput[0] == -1)
                if(command[0] == -1)
                {
                    // in case of invalid input, print message and continue with next loop
                    Console.WriteLine("Invalid input parameters.");
                    continue;
                }
                // check commandIndex for the first two commands 
                // (reverse and sort) and do what should be done
                if (command[0] < 2)
                {
                    // if it is reverse
                    if (command[0] == 0) Array.Reverse(theArray, command[2], command[1]);
                    // if it is sort
                    else Array.Sort(theArray, command[2], command[1]);
                }
                else
                {
                    // if the commands are roll left or right 
                    // declare the count and take its value
                    int count = command[1];
                    // declare two temporal arrays to split the original one
                    string[] leftSide, rightSide;
                    // check if we have "rollRight" (commandIndex = 3)
                    // if that is the case, "mirror" the two temporal arrays dimensions
                    if (command[0] != 2) count = theArray.Length - count;
                    // Initialize two temporal arrays leftSide and rightSide
                    // with their dimensions according "count" and swap their positions
                    // in the original array
                    leftSide = new string[count];
                    rightSide = new string[theArray.Length - count];
                    Array.Copy(theArray, 0, leftSide, 0, count);
                    Array.Copy(theArray, count, rightSide, 0, rightSide.Length);
                    Array.Copy(rightSide, 0, theArray, 0, rightSide.Length);
                    Array.Copy(leftSide, 0, theArray, rightSide.Length, leftSide.Length);
                }

            }
            Console.WriteLine("["+ string.Join(", ", theArray) +"]");
        }

        private static int[] RefineInput(string[] input, int length)
        {
            /*  This method takes the input as string array, together with the length   
             *  of the original initial array. The method returns integer array in the  
             *  following format [ commandIndex, count, start ], where:                 
             *      commandIndex - is the input command:
             *          0 - reverse;
             *          1 - sort;
             *          2 - rollLeft;
             *          3 - rollRight;
             *  !!! If the command is not valid, all members of the return array are negative values !!!
             *      count - converted to integer from input. It depends of the type of command
             *              if, the command is reverse or sort, count can't be greater
             *              than the length of original array. 
             *      start - converte to integer from the input. It is -1 if the input
             *              does not include it or -2 if input include it but it is not valid
             */
            int[] com = new int[3];
            // return commandIndex
            switch (input[0])
            {
                case "reverse": com[0] = 0; break;
                case "sort": com[0] = 1; break;
                case "rollLeft": com[0] = 2; break;
                case "rollRight": com[0] = 3; break;
            }
            // Check the type of command and return count
            int count = (input.Length > 3) ? int.Parse(input[4]) : int.Parse(input[1]);
            // check the type of command and return start if exist
            int start = (input.Length > 3) ? int.Parse(input[2]) : -1;
            // if command is reverse or sort check both count and start else only count
            if (input.Length > 3)
            {
                if(start < 0 || start >= length || count < 0 || start + count > length) return new int[] { -1, -1, -2 };
            }
            else
            {
                // if the command is rollLeft or rollRight, the input count is allowed to be 
                // greater than length of the original array, but to be used for manipulation
                // it should be reduced do module of the length
                count = count % length;
                if(count < 0 || count > length) return new int[]{-1, -1, -1};
            }
            // add parsed and refined input parameters to the array
            com[1] = count;
            com[2] = start;

            return com;
        }
    }
}
