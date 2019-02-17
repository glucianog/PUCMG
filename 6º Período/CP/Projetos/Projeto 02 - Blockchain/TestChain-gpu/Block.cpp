//
// Created by Dave Nash on 20/10/2017.
//

#include "Block.h"
#include "sha256.h"


Block::Block(uint32_t nIndexIn, const string &sDataIn) : _nIndex(nIndexIn), _sData(sDataIn)
{
    _nNonce = 0;
    _tTime = time(nullptr);

    sHash = _CalculateHash();
}

void Block::MineBlock(uint32_t nDifficulty)
{
    char cstr[nDifficulty + 1];
    for (uint32_t i = 0; i < nDifficulty; ++i)
    {
        cstr[i] = '0';
    }
    cstr[nDifficulty] = '\0';
    string aux = "";

    string str(cstr);
    const char* resp = (char*) malloc (sizeof(char) * 65);


    while ( aux != str)
    {
        _nNonce++;
        resp = _CalculateHash().c_str();
        aux = string(resp).substr(0, nDifficulty);
    }

    cout << "Block mined: " << string(resp) << endl;
}

inline string Block::_CalculateHash() const
{
    stringstream ss;
    ss << _nIndex << sPrevHash << _tTime << _sData << _nNonce;

    return sha256(ss.str());
}


    // #pragma omp parallel shared(resp)
    // {
    //     #pragma omp single nowait
    //     {
    //         while (_nNonce < 10000) {
    //             _nNonce++;

    //             #pragma omp target data map(tofrom:resp)
    //             #pragma omp task firstprivate(resp)
    //             resp = _CalculateHash().c_str();
    //         }
    //     }
    // }

