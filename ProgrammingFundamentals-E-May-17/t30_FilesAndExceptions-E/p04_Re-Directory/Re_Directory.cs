using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p04_Re_Directory
{
    class Re_Directory
    {
        static void Main(string[] args)
        {
            string[] files = Directory.GetFiles(@"../../Resources/input");
            string output = @"../../Resources/output/";
            Directory.CreateDirectory(output);
            foreach (string file in files)
            {
                string ext = file.Substring(file.LastIndexOf(".") + 1);
                Directory.CreateDirectory(output + ext + "s");
                string name = file.Substring(file.LastIndexOf("\\"));
                Directory.Move(file, output + ext + "s\\" + name);
            }
        }
    }
}
