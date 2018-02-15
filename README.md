# shared
Code shared between the phone app and pi program

## Setup
Edit your project's module `.iml` file by adding `<sourceFolder url="file://$MODULE_DIR$/src/shared/src/main/java" isTestSource="false" />` to the section with loads of other `sourceFolder`
 entries.
 
For the phone app, this file is `app/app.iml`.

Here is an edited `.iml` file with the added sourceFolder entry
![memes](https://image.prntscr.com/image/IOj0Gpt5TWKiri2qI1_QxQ.png)

You may also need to add this to your module dependencies, by adding a module dependency to your main module
![memes 2](https://image.prntscr.com/image/PkIr3aYfSCyNUty4cJf17w.png)

## Updating
After pulling from the phone or pi repo, run `git submodule update --remote` to update the shared code.
