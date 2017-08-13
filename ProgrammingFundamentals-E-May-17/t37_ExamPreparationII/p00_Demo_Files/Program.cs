using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace p04_Files
{
    class File
    {
        public string Root { get; set; }
        public string Path { get; set; }
        public string Name { get; set; }
        public string Extension { get; set; }
        public ulong Size { get; set; }

        public int CompareTo(File other)
        {
            return this.Size.CompareTo(other.Size);
        }

        public bool Equals(File other)
        {
            if (!this.Path.Equals(other.Path)) return false;
            return true;
        }

        public File(string path, string name, string extension, ulong size)
        {
            this.Path = path;
            this.Name = name;
            this.Extension = extension;
            this.Size = size;
        }

        public File(string input)
        {
            Match m = Regex.Match(input, @"^(?'root'[^\\]+)(?'path'.*\\(?'file'.+)\.(?'ext'.+));(?'size'[0-9]+)");
            this.Root = m.Groups["root"].Value;
            this.Path = m.Groups["path"].Value;
            this.Name = m.Groups["file"].Value;
            this.Extension = m.Groups["ext"].Value;
            this.Size = ulong.Parse(m.Groups["size"].Value);
        }
    }
    class Files
    {
        static void Main(string[] args)
        {
            // Dictionary<root, List<File(path, name, extension, size)>>
            Dictionary<string, List<File>> files = new Dictionary<string, List<File>>();
            int inputsCount = int.Parse(Console.ReadLine());
            for (int i = 0; i < inputsCount; i++)
            {
                // create File object with constructor that reads a input line
                // and use regex to recognize file fields
                File file = new File(Console.ReadLine());
                //string input = Console.ReadLine();
                //string[] tokens = input.Split('\\');
                //string root = tokens[0];
                //string[] filename = tokens[tokens.Length - 1].Split(new char[] { '.', ';' }, StringSplitOptions.RemoveEmptyEntries);
                //string path = string.Join("\\", tokens.Skip(1).Take(tokens.Length - 2));
                //string name = string.Join(".", filename.Take(filename.Length - 2));
                //string ext = string.Join("", filename.Skip(filename.Length - 2).Take(1));
                //ulong size = ulong.Parse(string.Join("", filename.Skip(filename.Length - 1).Take(1)));
                //File file = new File(path, name, ext, size);
                if (!files.ContainsKey(file.Root)) files.Add(file.Root, new List<File>());
                if (files[file.Root].Any(x => x.Equals(file)))
                {
                    files[file.Root].First(x => x.Equals(file)).Size = file.Size;
                }
                else
                {
                    files[file.Root].Add(file);
                }

            }
            Match m = Regex.Match(Console.ReadLine(), @"(?'ext'\w+) in (?'path'[^\n]+)");
            string reqExt = m.Groups["ext"].Value;
            string reqRoot = m.Groups["path"].Value;
            if (files.ContainsKey(reqRoot))
            {
                List<File> output = files[reqRoot]
                    .Where(f => f.Extension.Equals(reqExt))
                    .OrderByDescending(f => f.Size)
                    .ThenBy(f => f.Name).ToList();
                // Dictionary<string, List<File>> output = files;
                if (output.Count > 0)
                    foreach (File file in output)
                        Console.WriteLine("{0}.{1} - {2} KB", file.Name, file.Extension, file.Size);
                else
                {
                    Console.WriteLine("No");
                }
            }
            else
            {
                Console.WriteLine("No");
            }
        }
    }
}

