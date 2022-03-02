package ma.octo.aop.repository.impl;

import ma.octo.aop.entity.Language;
import ma.octo.aop.mapper.LanguageMapper;
import ma.octo.aop.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component
public class LanguageH2RepositoryImpl implements LanguageRepository {

    private final LanguageMapper languageMapper = new LanguageMapper();
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Language> findByExtension(String extension) {
        Language language = (Language) jdbcTemplate.queryForObject(
                "select * from languages where file_extension = ?",
                new Object[]{extension}, languageMapper);

        return Optional.ofNullable(language);
    }

    @Override
    public Optional<Language> findById(String id) {
        Language language = (Language) jdbcTemplate.queryForObject(
                "select * from languages where id = ?",
                new Object[]{id}, languageMapper);

        return Optional.ofNullable(language);
    }

    @Override
    public List<Language> findAll() {
        return (List<Language>) jdbcTemplate.query("select * from languages",
                languageMapper);
    }


}
