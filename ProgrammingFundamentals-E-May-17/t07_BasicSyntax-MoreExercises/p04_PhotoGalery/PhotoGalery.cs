using System;

namespace p04_PhotoGalery
{
    class PhotoGalery
    {
        static void Main(string[] args)
        {
            int[] meta = new int[9];
            for (int i = 0; i < meta.Length; i++)
            {
                meta[i] = int.Parse(Console.ReadLine());
            }
            Console.WriteLine($"Name: DSC_{meta[0]:D4}.jpg");
            Console.WriteLine($"Date Taken: {meta[1]:D2}/{meta[2]:D2}/{meta[3]:D4} {meta[4]:D2}:{meta[5]:D2}");
            #region HumanReadableFileSizeFormat
            // copied from https://stackoverflow.com/questions/281640/how-do-i-get-a-human-readable-file-size-in-bytes-abbreviations-using-net
            string[] sizes = { "B", "KB", "MB", "GB", "TB" };
            int order = 0;
            double len = meta[6];
            while (len >= 1024 && order < sizes.Length - 1)
            {
                order++;
                len /= 1000;
            }
#endregion
            Console.WriteLine($"Size: {len:0.#}{sizes[order]}");
            string orientation = "landscape";
            if (meta[7] < meta[8]) orientation = "portrait";
            if (meta[7] == meta[8]) orientation = "square";
            Console.WriteLine($"Resolution: {meta[7]}x{meta[8]} ({orientation})");
        }
    }
}
