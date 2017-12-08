DropSourceLogin Test README
TEST NOTES

BUILT  12.06.2017
TESTER Rhonda Oglesby

ENVIRONMENT
Windows 8.1 Pro
Chrome 62
Eclipse Oxygen.1a

TOOLS
Java 1.8.0_152
Maven 3.7.0
TestNG 6.13.1
Selenium 3.8.1

TEST
Verify each email error message on the login screen when using a valid password.
Verify each password error message on the login screen when using a valid password.

@BEFORE
Before this project can be run the name of the chromedriver.exe needs to be changed.
1. Go to the DropSourceLogin folder.
2. Right click on "chromedriver.exe.remove".
3. Select "Rename".
4. Rename the file "chromedriver.exe".

PAGE OBJECTS
email input text field
password input text field
email error message
password error message
login button

NOT TESTED
This is not a boundary test on the number of ways that should trigger the error messages, only that the messages fire on the happy path.

Data to do boundary testing:
EMAIL
"abc"
"123"
"a@b.c"
"1@2.3"
"a@b@c"
"1.2.3"
"@b.c"
"a@b"
"a@@b.c"
"a@b..c"
" a@b.c"
"a@b.c "
" a@b.c "
"a.b@c.d"
"a.b.c@d.e"

PASSWORD
"123"
"abc"
"Abc4567"
"abcdefgH"
"12345678"
"Abc456789..." (length = 63)
"Abc456789..." (length = 64)
"Abc456789..." (length = 65)
"Abc4$678"
"Abc4567."
" Abc4567"
"Abc4567 "
" Abc45678"
"Abc45678 "

COMMENTS
The test ran successfully on the happy path but I kept tweeking it and eventually the valid email address got locked out. 

If I had had more time I would have included all the boundary testing on the input fields. 




