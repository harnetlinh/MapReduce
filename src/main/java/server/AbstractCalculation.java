package server;

import java.io.IOException;

abstract class AbstractCalculation
{
    public abstract String getData();

    public abstract void Run() throws IOException;

}
