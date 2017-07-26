using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace p01_FilterExtensions
{
    class FilterExtensions
    {
        static void Main(string[] args)
        {
            string sourceDir = @"input";
            string extension = Console.ReadLine();
            try
            {
                List<string> files = Directory
                    .GetFiles(sourceDir, "*." + extension)
                    .Select(Path.GetFileName)
                    .ToList();
                foreach (string file in files)
                {
                    Console.WriteLine(file);
                }
            }
            catch (Exception e)
            {
                Console.WriteLine("Cant find the folder");
                Console.WriteLine(e.Message);
                throw;
            }
        }
    }
}
