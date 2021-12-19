# CMPE494/AES
## Group 6 AES Project

We already provided AES.class in code where you can run with following command 

    java AES option key_file input_file -len 192/256[optional] -mode cbc/ebc[optional]

  
 ---
 

    option
  For option you can type e for encryption and d for decryption. Option argument is mandatory.

	key_file
Key File is a mandatory argument which represents path to the key file.

	input_file
	
Key File is a mandatory argument which represents path to the input file.

	-len 192/256 [optional]
This is an optional argument, after -len flag 192 represents 192 bit key length and 256 represents 256 bit key length. If this option is not used default key length is 128 bit.

	-mode cbc/ebc [optional]
This is an optional argument, after -mode flag ebc represents Electronic Code Book (ECB) mode, and  cbc represents Cipher-Block Chaining mode.  If this option not used ecb is default option.


**Important Note:** We haven't used any libraries to parse flags. So, if both    -mode and -len flag will be used. -len flag should be used first.
