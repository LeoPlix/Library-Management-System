all:
	(cd bci-core; make $(MFLAGS) all)
	(cd bci-app; make $(MFLAGS) all)

clean:
	(cd bci-core; make $(MFLAGS) clean)
	(cd bci-app; make $(MFLAGS) clean)

install:
	(cd bci-core; make $(MFLAGS) install)
	(cd bci-app; make $(MFLAGS) install)

