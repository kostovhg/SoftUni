using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p03_Portal
{
    class Program
    {
        static void Main(string[] args)
        {
            int rows = int.Parse(Console.ReadLine());
            char[][] matrix = new char[rows][];
            int cRow = 0, cCol = 0;
            for (int r = 0; r < rows; r++)
            {
                matrix[r] = Console.ReadLine().ToCharArray();
                for (int c = 0; c < matrix[r].Length; c++)
                {
                    if (matrix[r][c] == 'S')
                    {
                        cRow = r;
                        cCol = c;
                    }
                }
            }
            char[] directions = Console.ReadLine().ToCharArray();
            int steps = 0;
            bool finish = false;
            foreach (char ch in directions)
            {
                switch (ch)
                {
                    case 'L':
                        if (cCol == 0) cCol = matrix[cRow].Length - 1;
                        else cCol--;
                        break;
                    case 'R':
                        if (cCol + 1 == matrix[cRow].Length) cCol = 0;
                        else cCol++;
                        break;
                    case 'U':
                        for (int r = cRow - 1; ; r--)
                        {
                            if (r == -1) r = rows - 1;
                            if (matrix[r].Length > cCol)
                            {
                                cRow = r;
                                break;
                            }
                        }
                        break;
                    case 'D':
                        for (int r = cRow + 1;  ; r++)
                        {
                            if (r == rows) r = 0;
                            if (matrix[r].Length > cCol)
                            {
                                cRow = r;
                                break;
                            }
                        }
                        break;
                }
                steps++;
                if (matrix[cRow][cCol] == 'E')
                {
                    finish = true;
                    break;
                }
            }
            if (finish)
            {
                Console.WriteLine($"Experiment successful. {steps} turns required.");
            }
            else
            {
                Console.WriteLine($"Robot stuck at {cRow} {cCol}.Experiment failed.");
            }

        }
    }
}
