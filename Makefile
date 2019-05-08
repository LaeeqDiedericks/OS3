

JAVAC=/usr/bin/javac
.SUFFIXES: .java .class

SRCDIR=src
BINDIR=bin
DOCDIR=doc

SOURCES=$(shell find $(SRCDIR) -name '*.java' -type f)
CLASSES=$(SOURCES:$(SRCDIR)/%.java=$(BINDIR)/%.class)

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(SRCDIR) $<

compile: $(CLASSES)

clean:
	rm $(BINDIR)/*.class

temp:
	echo $(CLASSES)

run: 
	java -cp ./bin RunSimulation 12 2




