package org.tton.hrm.service;

import java.util.List;

import org.tton.hrm.domain.Job;
import org.tton.hrm.util.tag.PageModel;

/**
 * ClassName: JobService <br/>
 * Description: TODO <br/>
 * Date: 2018��1��18�� ����5:08:01 <br/>
 * <br/>
 * 
 * @author hanyouchuan(����)
 * 
 *         �޸ļ�¼
 * @version ��Ʒ�汾��Ϣ yyyy-mm-dd ����(����) �޸���Ϣ<br/>
 * 
 */

public interface JobService {
    /**
     * �������ְλ
     * 
     * @return Job�����List����
     * */
    List<Job> findAllJob();

    List<Job> findJob(Job job, PageModel pageModel);

    /**
     * ���idɾ��ְλ
     * 
     * @param id
     * */
    public void removeJobById(Integer id);

    /**
     * ���ְλ
     * 
     * @param Job
     *            ���Ŷ���
     * */
    void addJob(Job job);

    /**
     * ���id��ѯְλ
     * 
     * @param id
     * @return ְλ����
     * */
    Job findJobById(Integer id);

    /**
     * �޸�ְλ
     * 
     * @param dept
     *            ���Ŷ���
     * */
    void modifyJob(Job job);
}
