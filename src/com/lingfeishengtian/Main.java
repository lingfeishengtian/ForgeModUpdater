package com.lingfeishengtian;

import javax.swing.*;
import java.io.File;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) {
        try{
            String path = System.getProperty("user.home");
            File mods = new File(getFileLocation().getAbsolutePath() + File.separator + "mods");
            String modlib = path + "/Library/Application Support/minecraft/";
            if(System.getProperty("os.name").contains("Windows")){
                modlib = path + "\\AppData\\Roaming\\.minecraft\\";
            }
            File modsDir = new File(modlib + "mods");
            System.out.println(modlib + "modsBak");
            File modBak = new File(modlib + "modsBak");
            if(modBak.exists()) System.out.println(deleteDirectory(modBak));
            if(modsDir.exists()) {
                renameFile(modBak.getAbsolutePath(), modsDir);
            }
            System.out.println(mods.getAbsolutePath());
            renameFile(modlib + "mods", mods);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("An error occurred.");
        }
    }

    public static boolean deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            File[] children = dir.listFiles();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDirectory(children[i]);
                if (!success) {
                    return false;
                }
            }
        }

        // either file or an empty directory
        System.out.println("removing file or directory : " + dir.getName());
        return dir.delete();
    }

    private static void renameFile(String modlib, File modsDir) {
        if (modsDir.renameTo(new File(modlib))) {
            System.out.println("File moved successfully");
        } else {
            JFrame f;
            f=new JFrame();
            JOptionPane.showMessageDialog(f,"Please close Minecraft before you run this program..","Alert",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    public static File getFileLocation() throws URISyntaxException {
        return new File(Main.class.getProtectionDomain().getCodeSource().getLocation()
                .toURI()).getParentFile();
    }
}
