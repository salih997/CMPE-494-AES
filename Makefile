JC = javac

AES.class: AES.java
	$(JC) AES.java

clean:
	$(RM) AES.class