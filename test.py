from math import *
from sympy import *
x = Symbol("x")
y = Symbol("y")
z = Symbol("z")
a = Symbol("a")
b = Symbol("b")
c = Symbol("c")
t = Symbol("t")
i=0
result = "Error"
input = open('output.txt', 'r')
output = open('input.txt', 'w')
s = input.readline().rstrip()
try:
    if s[:5] != "solve":
        s = s.split()
    else:
        s = s[6 :].split(", ") 
        result = solve(s, [x, y, z, t, a, b, c])
    if s[0] == "limit":
        result = limit(s[1], s[2], s[3])
    elif s[0] == "diff":
        if len(s) < 3:
            result = diff(s[1], s[2], 1)
        else:
            result = diff(s[1], s[2], s[3])
    elif s[0] == "integrate":
        if len(s) == 3:
            result = integrate(s[1], x)
        else:
            result = integrate(s[1], (x, s[3], s[4]))
    elif s[0] == "sqrt":
            result = sqrt(float(s[1]))
    elif s[1] == "pow":
            result = pow(float(s[0]), float(s[2]))
    elif s[0] == "fabs":
            result = fabs(float(s[1]))
    elif s[0] == "pi":
            result = pi
    elif s[0] == "e":
            result = e 
    elif s[0] == "sin":
        if s[1].split("/")[0].isalnum:
            result = (str(sin(s[1])) + "; " + str(float(sin(s[1]))))
        else:
            result = sin(str(s[1])).expand(trig=True)
    elif s[0] == "cos":
        if s[1].split("/")[0].isalnum:
            result = (str(cos(s[1])) + "; " + str(float(cos(s[1]))))
        else:
            result = cos(str(s[1])).expand(trig=True)
    elif s[0] == "tan":
        if s[1].split("/")[0].isalnum:
            result = (str(tan(s[1])) + "; " + str(float(tan(s[1]))))
        else:
            result = tan(s[1]).expand(trig=True)
    elif s[0] == "ctg":
        if s[1].split("/")[0].isalnum:
            result = "1/(" + (str(tan(s[1])) +")" + "; " + str(float(1 / tan(s[1]))))
        else:
            result = 1 / tan(s[1]).expand(trig=True)
    elif s[0] == "asin":
            result = asin(s[1])
    elif s[0] == "acos":
            result = acos(s[1])
    elif s[0] == "atan":
            result = atan((s[1]))
    elif s[0] == "sinh":
            result = sinh(float(s[1]))
    elif s[0] == "cosh":
            result = cosh(float(s[1]))
    elif s[0] == "tanh":
            result = tanh(float(s[1])) 
    elif s[1] == "factorial":
        result = factorial (int(s[0]))
    elif s[0] == "log":
            result = log(float(s[1]), float(s[2]))
    elif s[0] == "ln":
            result = log(float(s[1]), e)
    elif s[0] == "matrix":
        matrixA = Matrix( int(s[1]), int(s[2]), [int (i) for i in s[3:]])
        s2 = input.readline().rstrip()
        if (s2 == "+") or (s2 == "*") or (s2 == "solve"):
            s3 = input.readline().rstrip().split()
            matrixB = Matrix(int(s3[0]), int(s3[1]), [int (i) for i in s3[2:]])
            if s2 == "+":
                result = (matrixA+matrixB)
                result = str(result)[7:-1]
            elif s2 == "*":
                result = (matrixA*matrixB)
                result = str(result)[7:-1]
        if s2 == "-1":
            result = (matrixA.inv("LU"))
            result = str(result)[7:-1]
        if s2 == "solve":
            result = (matrixA.QRsolve(matrixB))
            result = str(result)[7:-1]
        if s2 == "t":
            result = (matrixA.transpose())
            result = str(result)[7:-1]
        if s2 == "det":
            result = (matrixA.det())
   
except Exception:
    result = "Error"
output.write(str(result))
input.close()
output.close()
