import math as m

def scalarProduct(a, b):
    assert(len(a) == len(b))
    res = 0
    for i in range(len(a)):
        res += a[i] * b[i]
    return res


def getLength(a):
    res = 0
    for i in a:
        res += m.pow(i, 2)
    return m.sqrt(res)

def getAngle(a, b):
    assert(len(a) == len(b))
    top = scalarProduct(a, b)
    bottom = getLength(a) * getLength(b)
    return m.acos(top / bottom)
    #return top / bottom

print( m.degrees(getAngle([1, 0, 0], [0, 1, 0])) )
