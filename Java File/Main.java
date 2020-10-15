import java.io.*;
import java.util.Scanner;

public class Main {

    Scanner sc = new Scanner(System.in);

    private void addNote() {
        String path = "C:/File Keeper JAVA";
        File folder = new File(path);

        if(!folder.exists()) {
            folder.mkdir();
        }

        String fileList[] = folder.list();
        int fileCounter = 0;

        for(String name : fileList) {
            fileCounter++;
        }

        System.out.println("Type Heading");
        try {
            String fileName = sc.nextLine();
            File file = new File(path + "/" + fileCounter + ". " + fileName + ".txt");
            file.createNewFile();

            PrintWriter write = new PrintWriter(file);
            System.out.println("---____Write Your Text Here____---");
            String note = sc.nextLine();
            write.println(note);
            write.close();
            write.flush();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showNoteList() {
        String path = "C:/File Keeper JAVA";
        File folder = new File(path);

        if(!folder.exists()) {
            folder.mkdir();
        }

        String fileList[] = folder.list();

        System.out.println();
        System.out.println();
        System.out.println("----------------------------------------- Note List -----------------------------------------");


        for(String name : fileList) {
            if (name.indexOf(".") > 0)
                name = name.substring(0, name.lastIndexOf("."));
            System.out.println(name);
        }
        System.out.println("---------------------------------------------------------------------------------------------");
    }

    private void getNote() {
        BufferedReader reader = null;
        showNoteList();
        System.out.println();
        System.out.println("Enter the index number of the file given on first of the file name");
        int index = sc.nextInt();

        String path = "C:/File Keeper JAVA";
        File folder = new File(path);

        if(!folder.exists()) {
            folder.mkdir();
        }

        String fileList[] = folder.list();

        int li = 0;
        int hi = fileList.length-1;

        while(li <= hi) {
            int mi = (li+hi)/2;
            String nameMiddleIndex = fileList[mi].substring(0, fileList[mi].indexOf('.'));

            if(index == Integer.parseInt(nameMiddleIndex)) {
                try {
                    String name = fileList[mi];
                    reader = new BufferedReader(new FileReader("C:/File Keeper JAVA/" + name));
                    String line;

                    System.out.println();
                    System.out.println();
                    System.out.println("----------------------------------------- Note Text -----------------------------------------");
                    System.out.println();

                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                    reader.close();
                } catch (Exception e ) {
                    System.out.println(e.getMessage());
                }
                System.out.println();
                System.out.println("---------------------------------------------------------------------------------------------");
                return;
            }

            else if (index < Integer.parseInt(nameMiddleIndex)) {
                hi = mi - 1;
            }

            else {
                li = mi + 1;
            }
        }
        System.out.println("Not Found");
    }

    public void deleteNote() {
        showNoteList();
        System.out.println();
        System.out.println("Enter the index number of the file given on first of the file name");
        int index = sc.nextInt();
        int filecounter;

        String path = "C:/File Keeper JAVA";
        File folder = new File(path);

        if(!folder.exists()) {
            folder.mkdir();
        }

        String fileList[] = folder.list();

        int li = 0;
        int hi = fileList.length-1;

        while(li <= hi) {
            int mi = (li+hi)/2;
            String nameMiddleIndex = fileList[mi].substring(0, fileList[mi].indexOf('.'));

            if(index == Integer.parseInt(nameMiddleIndex)) {
                File file = new File("C:/File Keeper JAVA/" + fileList[mi]);
                filecounter = Integer.parseInt(nameMiddleIndex);

                boolean b = file.delete();
                System.out.println(b);
                System.out.println();
                System.out.println();
                System.out.println("Note Deleted");
                int c = filecounter;
                for(String name : fileList) {

                    if(Integer.parseInt(name.substring(0,name.indexOf('.'))) > filecounter) {
                        File prevName = new File("C:/File Keeper JAVA/" + name);
                        String s = name.substring(0,name.indexOf('.'));
                        String newName = name.replace(s,Integer.toString(c));
                        File rename = new File("C:/File Keeper JAVA/" + newName);
                        prevName.renameTo(rename);
                        c++;

                    }
                }
                return;
            }

            else if (index < Integer.parseInt(nameMiddleIndex)) {
                hi = mi - 1;
            }

            else {
                li = mi + 1;
            }
        }
        System.out.println("Not Found");
    }

    public void mainMenu() {
        System.out.println(" -: Menu :- ");
        System.out.println();
        System.out.println("1. Get Note");
        System.out.println("2. Add Note");
        System.out.println("3. Delete Note");
        System.out.println("4. Exit");

        int choice = sc.nextInt();

        switch(choice) {
            case 1 :
                getNote(); break;

            case 2 :
                System.out.println();
                System.out.println();
                sc.nextLine();
                addNote(); break;

            case 3 :
                deleteNote(); break;

            case 4 :
                System.exit(0); break;

            default :
                System.out.println("Wrong Input");
        }

    }

    public static void main(String[] args) {

        Main noteKeeper = new Main();
        for(;;) {
            noteKeeper.mainMenu();
        }
    }
}
