using System;

namespace p03_MirrorImage
{
    class MirrorImage
    {
        static void Main(string[] args)
        {
            string[] arr = Console.ReadLine().Split();
            string index = Console.ReadLine();
            while (!index.Equals("Print"))
            {
                int i = int.Parse(index);
                if (i > 1)
                {
                    reverse(arr, 0, i - 1);
                }
                if(i < arr.Length - 2)
                {
                    reverse(arr, i + 1, arr.Length - 1);
                }
                index = Console.ReadLine();
            }
            Console.WriteLine(string.Join(" ", arr));
        }

        private static void reverse(string[] arr, int start, int end)
        {
            int c = 0;
            for (int i = start; i <= ((end - start) / 2) + start; i++, c++)
            {
                string tmp = arr[i];
                arr[i] = arr[end - c];
                arr[end - c] = tmp;
            }
        }
    }
}
