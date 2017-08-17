using System;
using System.Collections.Generic;
using System.Linq;
namespace p02_ArrayManipulator
{
    class ArrayManipulator
    {
        static void Main(string[] args)
        {
            int[] theArray = Console.ReadLine().Split().Select(int.Parse).ToArray();
            string[] input;
            while (!"end".Equals((input = Console.ReadLine().Split().ToArray())[0]))
            {
                switch (input[0])
                {
                    case "exchange": Exchange(theArray, input[1]); break;
                    case "max": MaxElement(theArray, input[1]);  break;
                    case "min": MinElement(theArray, input[1]); break;
                    case "first": FirstElements(theArray, input[1], input[2]); break;
                    case "last": LastElements(theArray, input[1], input[2]); break;
                }
            }
            Console.WriteLine("["+string.Join(", ", theArray)+"]");
        }

        private static void LastElements(int[] theArray, string v1, string v2)
        {
            int count = int.Parse(v1);
            if (count > theArray.Length)
            {
                Console.WriteLine("Invalid count");
                return;
            }
            int even = ("even".Equals(v2)) ? 0 : 1;
            List<int> tmp = new List<int>();
            for (int i = theArray.Length - 1; i >= 0; i--)
            {
                if (theArray[i] % 2 == even && tmp.Count < count)
                {
                    if (theArray.Length == 0)
                    {
                        tmp.Add(theArray[i]);
                    }
                    else
                    {
                        tmp.Insert(0, theArray[i]);
                    }
                }
            }
            Console.WriteLine("[" + string.Join(", ", tmp) + "]");
        }

        private static void FirstElements(int[] theArray, string v1, string v2)
        {
            int count = int.Parse(v1);
            if (count > theArray.Length)
            {
                Console.WriteLine("Invalid count");
                return;
            }
            int even = ("even".Equals(v2)) ? 0 : 1;
            List<int> tmp = new List<int>();
            for (int i = 0; i < theArray.Length; i++)
            {
                if (theArray[i] % 2 == even && tmp.Count < count)
                {
                    tmp.Add(theArray[i]);
                }
            }
            Console.WriteLine("[" + string.Join(", ", tmp) + "]");
        }

        private static void MinElement(int[] theArray, string v)
        {
            int even = ("even".Equals(v)) ? 0 : 1;
            int min = int.MaxValue;
            int index = -1;
            for (int i = 0; i < theArray.Length; i++)
            {
                if (theArray[i] % 2 == even && theArray[i] <= min)
                {
                    min = theArray[i];
                    index = i;
                }
            }
            if (index > -1)
            {
                Console.WriteLine(index);
            }
            else
            {
                Console.WriteLine("No matches");
            }
        }

        private static void MaxElement(int[] theArray, string v)
        {
            int even = ("even".Equals(v)) ? 0 : 1;
            int max = int.MinValue;
            int index = -1;
            for (int i = 0; i < theArray.Length; i++)
            {
                if (theArray[i] % 2 == even && theArray[i] >= max)
                {
                    max = theArray[i];
                    index = i;
                }
            }
            if (index > -1)
            {
                Console.WriteLine(index);
            }
            else
            {
                Console.WriteLine("No matches");
            }
        }
           

        private static void Exchange(int[] theArray, string v)
        {
            int index = int.Parse(v);
            if(index <0 || index >= theArray.Length)
            {
                Console.WriteLine("Invalid index");
                return;
            }
            if (index == theArray.Length - 1) return;
            int[] left = new int[index + 1];
            int[] right = new int[theArray.Length - index - 1];
            Array.Copy(theArray, 0, left, 0, index + 1);
            Array.Copy(theArray, index + 1, right, 0, right.Length);
            Array.Copy(right, 0, theArray, 0, right.Length);
            Array.Copy(left, 0, theArray, right.Length, left.Length);
        }
    }
}
