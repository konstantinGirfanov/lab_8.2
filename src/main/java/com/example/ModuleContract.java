package com.example;

public interface ModuleContract
{
    boolean CanBeExecuted(String filePath);
    String GetDescription();
    void Execute(String filePath);
}