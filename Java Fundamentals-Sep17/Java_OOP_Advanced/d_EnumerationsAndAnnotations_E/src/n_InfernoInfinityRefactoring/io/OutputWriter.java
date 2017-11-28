package n_InfernoInfinityRefactoring.io;

/**
 *  This is the interface for the OutputWriter
 *  @method void writeLine(String output) - a method wich is made to write a string line to the corresponding Stream.
 *  @method vodi writeLine(String format, Object... params) - a method wich is made write a string line to the corresponding Stream, formatted in a specific way, with the given parameters.
 */

public interface OutputWriter {

    void writeLine(String output);

    void writeLine(String format, Object... params);
}
