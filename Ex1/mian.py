
def foo(a=0,*args,**kwargs):
    print(args)
    print(kwargs)
    print(a)
foo(10,1,2,3,"dsgljerhg",b=4,l="7657")

