package com.midgardabc.day11.frame4;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by user on 15.09.2015.
 */
public class FileList<T> implements SimpleList<T> {

    private File list;
    private File halpFile;
    private FileInputStream fis;
    private Reader reader;
    private FileInputStream readHalp;
    private Reader readerHalp;
    private BufferedReader bufferedReader;
    private FileOutputStream fos;
    private Writer writer;
    private int size;
    private int countPlaces;
    private BufferedWriter bufferedWriter;
    private Writer halpWriter;
    private FileOutputStream outHalp;
    private BufferedWriter bufferedWriterHelp;

    public FileList() throws IOException {
        list = new File("SimpleList.txt");
        halpFile = new File("File.txt");
        list.createNewFile();
        halpFile.createNewFile();
        readHalp = new FileInputStream(halpFile.getName());
        readerHalp = new InputStreamReader(readHalp);
        outHalp = new FileOutputStream(halpFile.getName());
        halpWriter = new OutputStreamWriter(outHalp);
        bufferedWriterHelp = new BufferedWriter(halpWriter);
        fis = new FileInputStream(list.getName());
        reader = new InputStreamReader(fis);
        bufferedReader = new BufferedReader(reader);
        fos = new FileOutputStream(list.getName());
        writer = new OutputStreamWriter(fos);
        bufferedWriter = new BufferedWriter(writer);
    }

    @Override
    public void add(T object) {
         String obj = object.toString();

        try {
            halpWriter = new OutputStreamWriter(outHalp);
            bufferedWriterHelp = new BufferedWriter(halpWriter);
            if (size == 0) bufferedWriterHelp.write(obj);
            else bufferedWriterHelp.write("\n" + obj);
            size++;
            countPlaces++;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
          try {
              bufferedWriterHelp.flush();
           } catch (IOException e) {
               e.printStackTrace();
            }
        }
        try {
            readHalp = new FileInputStream(halpFile.getName());
            fos = new FileOutputStream(list.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        readerHalp = new InputStreamReader(readHalp);
        writer = new OutputStreamWriter(fos);
        add(readerHalp, writer, object);
        try {
            fis = new FileInputStream(list.getName());
            outHalp = new FileOutputStream(halpFile.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        copy(fis, outHalp);

    }

    @Override
    public boolean contains(T object) {
        BufferedReader bufferedReader = null;

        try {
            fis = new FileInputStream(list.getName());
            reader = new InputStreamReader(fis);
            bufferedReader = new BufferedReader(reader);
            String str;
            while ((str = bufferedReader.readLine()) != null) {
               if (str.equals(object.toString())) {
                   return true;
               }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
           try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void remove(T object) {
        try {
            fis = new FileInputStream(list.getName());
            outHalp = new FileOutputStream(halpFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        reader = new InputStreamReader(fis);
        halpWriter = new OutputStreamWriter(outHalp);
        delete(reader, halpWriter, object);
        try {
            readHalp = new FileInputStream(halpFile.getName());
            fos = new FileOutputStream(list.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        readerHalp = new InputStreamReader(readHalp);
        writer = new OutputStreamWriter(fos);
        delete(readerHalp, writer, object);
        size--;

    }

    public T get(int index) {
        if (index < 0 || index > countPlaces) {
            throw new IllegalStateException("OutOfIndexException. Index will be at 0 to list size!!!");
        }
        try {
            fis = new FileInputStream(list.getName());
            reader = new InputStreamReader(fis);
            bufferedReader = new BufferedReader(reader);

            int position = 0;
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                if (index == position) {
                    Object o = str;
                    T object = (T)o;
                    return object;
                }
                position++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void add(Reader input, Writer out, T object) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(input);
            writer = new BufferedWriter(out);
            boolean checkAdd = true;
            int i = 0;
            String currentString = null;
            String presentObject = null;
            String str;
            while ((str = reader.readLine()) != null) {
                currentString = str;
                if (str.equals("") && checkAdd) {
                    if (i == 0) writer.write(object.toString());
                    else writer.write("\n" + object.toString());
                    presentObject = object.toString();
                    checkAdd = false;
                } else {
                    if (presentObject != null && str.equals(presentObject)) {
                        continue;
                    }
                    if (i == 0) writer.write(str);
                    else writer.write("\n" + str);
                }
                    i++;
            }
            if (checkAdd && currentString == null) {
               if (i == 0) writer.write(object.toString());
                else writer.write("\n" + object.toString());
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.flush();
                }
                if (reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void copy(FileInputStream input, FileOutputStream out) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            Reader r = new InputStreamReader(input);
            Writer w = new OutputStreamWriter(out);
            reader = new BufferedReader(r);
            writer = new BufferedWriter(w);

            int i = 0;
            String str;
            while ((str = reader.readLine()) != null) {
                if (i == 0) writer.write(str);
                else writer.write("\n" + str);
                i++;
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.flush();
                }
                if (reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void delete(Reader input, Writer out, T object)  {

        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(input);
            writer = new BufferedWriter(out);

            int i = 0;
            String str;
            while ((str = reader.readLine()) != null) {
                    if (str.equals(object.toString())) {
                        if (i == 0) writer.write("");
                        else writer.write("\n");
                        if (i == countPlaces-1) {
                            countPlaces--;
                        }
                    } else {
                        if (i == 0) writer.write(str);
                        else writer.write("\n" + str);
                    }
                i++;
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.flush();
                }
                if (reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleIterator();
    }

    private class SimpleIterator implements Iterator {

        private int index;
        private Object cp;

        public SimpleIterator() {
            index = -1;
        }

        @Override
        public boolean hasNext() {
            return (get(index+1) != null);
        }

        @Override
        public Object next() {
            if (hasNext()) {
                cp = get(++index);
                return cp;
            }
            return null;
        }
    }
}
