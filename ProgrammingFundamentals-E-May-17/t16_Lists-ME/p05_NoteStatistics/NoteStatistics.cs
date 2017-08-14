using System;
using System.Collections.Generic;
using System.Linq;

namespace p05_NoteStatistics
{
    class NoteStatistics
    {
        static void Main(string[] args)
        {
            Dictionary<double, string> notesDic = new Dictionary<double, string>{
                { 261.63, "C" },
                { 277.18, "C#"},
                { 293.66, "D" },
                { 311.13, "D#"},
                { 329.63, "E" },
                { 349.23, "F" },
                { 369.99, "F#"},
                { 392.00, "G" },
                { 415.30, "G#"},
                { 440.00, "A" },
                { 466.16, "A#"},
                { 493.88, "B" },
            };

            List<double> freq = readDoubleList();
            List<string> notes = new List<string>(freq.Count());
            foreach (double freqVal in freq)
            {
                notes.Add(notesDic[freqVal]);
                // Console.Beep((int)freqVal, 600);
            }

            Console.WriteLine("Notes: {0}", string.Join(" ", notes));
            Console.WriteLine("Naturals: " + string.Join(", ", notes.Where(x => x.Length < 2)));
            Console.WriteLine("Sharps: " + string.Join(", ", notes.Where(x => x.Length > 1)));
            Console.WriteLine("Naturals sum: {0}", freq.Where(x => notesDic[x].Length < 2).Sum());
            Console.WriteLine("Sharps sum: {0}", freq.Where(x => notesDic[x].Length > 1).Sum());
        }

        private static List<double> readDoubleList()
        {
            List<double> list = Console.ReadLine().Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries).Select(double.Parse).ToList();
            return list;
        }
    }
}
