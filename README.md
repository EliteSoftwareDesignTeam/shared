# shared
Code shared between the phone app and pi program

## Setup
Edit your project's module `.iml` file by adding `<sourceFolder url="file://$MODULE_DIR$/src/shared/src/main/java" isTestSource="false" />` to the section with loads of other `sourceFolder`
 entries.
 
For the phone app, this file is `app/app.iml`.

Here is an edited `.iml` file with the added sourceFolder entry
![memes](https://prnt.sc/ie4sxu)

## Updating
After pulling from the phone or pi repo, run `git submodule update --remote` to update the shared code.
