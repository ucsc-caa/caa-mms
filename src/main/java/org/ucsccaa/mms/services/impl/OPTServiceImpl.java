package org.ucsccaa.mms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ucsccaa.mms.repositories.OPTRepository;
import org.ucsccaa.mms.domains.OPT;
import org.ucsccaa.mms.services.OPTService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class OPTServiceImpl implements OPTService {
    @Autowired
    private OPTRepository optRepository;

    @Override
    public Long createOPT(OPT opt) {
        if (opt == null) {
            throw new IllegalArgumentException("OPT can't be NULL");
        }
        return optRepository.save(opt).getId();
    }

    @Override
    public Optional<OPT> updateOPTByID(OPT opt) {
        if (opt == null || opt.getId() == null) {
            throw new IllegalArgumentException("OPT or id can't be NULL");
        }
        if (!optRepository.existsById(opt.getId())) {
            return Optional.empty();
        } else {
            return Optional.of(optRepository.save(opt));
        }
    }

    @Override
    public void deleteOPT(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID can't be NULL");
        }
        optRepository.deleteById(id);
    }

    @Override
    public Optional<OPT> findOPTByID(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID can't be NULL");
        }
        return optRepository.findById(id);
    }

    @Override
    public List<OPT> listAll() {
        return optRepository.findAll();
    }
}
