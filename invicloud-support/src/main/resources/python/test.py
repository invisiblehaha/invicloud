import pandas as pd
import sys

if __name__ == '__main__':
    print(sys.argv[0])
    data = pd.DataFrame(columns=["test"])
    data.to_csv(sys.argv[1]+'\\test.txt')
